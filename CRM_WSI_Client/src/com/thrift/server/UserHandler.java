package com.thrift.server;

import common.DESCoder;
import common.GetTokenResult;
import common.WebServiceExecuterNew;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.thrift.TException;
import org.dom4j.*;
import org.springside.modules.mapper.JaxbMapper;
import sun.misc.BASE64Encoder;
import org.apache.commons.io.IOUtils;
import common.Coder;

import org.dom4j.io.SAXReader;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;


import java.io.IOException;
import java.security.Key;


public class UserHandler implements com.thrift.server.AyService.Iface{

    public static Integer company_id = 6;
    public static String user_type = "member_user";
    public static String origin = "www.1897.com";

/*    private static InputStream inStream = UserHandler.class.getClassLoader().getResourceAsStream("code.properties");
    static {
        Properties properties = new Properties();
        try {
            properties.load(inStream);
            url = properties.get("url").toString();
            spPassword = properties.get("spPassword").toString();
            spName = properties.get("spName").toString();
            key = properties.get("key").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    private String getXml_encrypt(String key, String xml) throws Exception {

        Key k = DESCoder.toKey(key.getBytes());
        byte[] encryptData = DESCoder.encrypt(xml.getBytes(), k);
        String xml_encrypt = new BASE64Encoder().encode(encryptData); // 加密后的数据
        return xml_encrypt;

    }
    //
    public int addUser(com.thrift.server.User user) throws TException {

          String xml_encrypt = null;
          String sign = null;

        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<userInfo>" +
                    "<company_id>"+company_id+"</company_id>" +
                    "<mobiletel>"+user.getMobile()+"“”“”“”“”</mobiletel>" +
                    "<open_id>"+user.getWeixinOpenid()+"</open_id>" +
                    "<password>"+user.getPassword()+"</password>" +
                    "<user_type>"+user.getUser_type()+"</user_type>" +
                    "<area>"+user.getArea()+"</area>" +
                    "<city>"+user.getCity()+"</city>" +
                    "<main_babay_birthday>"+user.getBabybirthday()+"</main_babay_birthday>" +
                    "<main_babay_sex>"+user.getBabysex()+"</main_babay_sex>" +
                    "<member_name>"+user.getMemberUsername()+"</member_name>" +
                    "<member_no>"+user.getMobile()+"</member_no>" +
                    "<province>"+user.getProvince()+"</province>" +
                    "<address>"+user.getAddress()+"</address>" +
                    "<origin>"+user.getOrigin()+"</origin>" +
                    "<referrer>"+user.getAvatar()+"</referrer>" +
                    "</userInfo>";
            xml_encrypt = getXml_encrypt(AyServiceRun.key, xml);
            sign = Coder.sign(AyServiceRun.xml_encrypt.getBytes(), AyServiceRun.privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        String rsxmlx = WebServiceExecuterNew.getUserSoapService(AyServiceRun.url, AyServiceRun.spName, AyServiceRun.spPassword).register(AyServiceRun.result.getToken(), sign, xml_encrypt);
        System.out.println(rsxmlx);
        Document document = getDocument(rsxmlx);
        String msg="",memberId="0";
        HashMap map = new HashMap();
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element ele : childElements) {
            System.out.println(ele.getName() + ": " + ele.getText());
            if(ele.getName().equals("msg")){
                msg = ele.getText();
            }else if(ele.getName().equals("memberId")){
                memberId = ele.getText();
            }
        }


        if (msg!=null){
            if (msg.contains("成功")) {
                System.out.println("成功");
                return Integer.parseInt(memberId);
            }
            else if (msg.contains("已经注册")){
                System.out.println("已经注册");
                return 0;
            }
        }
        System.out.println("失败");

        return -1;
    }


    public User getUserInfo(String mobile){
        User user = new User();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo>><companyId>"+company_id+"</companyId><userName>"+mobile+"</userName></userQueryInfo>";
        String xml_encrypt="",sign="";
        try {
            xml_encrypt = getXml_encrypt(AyServiceRun.key, xml);
            sign = Coder.sign(AyServiceRun.xml_encrypt.getBytes(), AyServiceRun.privateKey);
            String rsxmlx = WebServiceExecuterNew.getUserSoapService(AyServiceRun.url, AyServiceRun.spName, AyServiceRun.spPassword).userQuery(AyServiceRun.result.getToken(), sign, xml_encrypt);
            Document document = getDocument(rsxmlx);
            HashMap map = new HashMap();
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();
            for (Element ele : childElements) {
                System.out.println(ele.getName() + ": " + ele.getText());
                map.put(ele.getName(), ele.getText());
            }
            Iterator keys = map.keySet().iterator();
            while(keys.hasNext()){
                String key = (String)keys.next();
                if("member_name".equals(key)){
                    user.setUsername((String) map.get(key));
                }else if("password".equals(key)){
                    user.setPassword((String) map.get(key));
                }else if("gender".equals(key)){
                    user.setGender((Byte) map.get(key));
                }else if("member_name".equals(key)){
                    user.setNickname((String) map.get(key));
                }else if("mobiletel".equals(key)){
                    user.setMobile((String) map.get(key));
                }else if("point_a_balance".equals(key)){
                    user.setIntegral(Integer.parseInt(map.get(key).toString()));
                }else if("tier_id".equals(key)){
                    user.setGrade(Integer.parseInt(map.get(key).toString()));
                }else if("main_babay_birthday".equals(key)){
                    user.setBabybirthday((String) map.get(key));
                }else if("main_babay_sex".equals(key)){
                    user.setBabysex(Byte.valueOf(map.get(key).toString()));
                }else if("member_name".equals(key)){
                    user.setMemberUsername((String) map.get(key));
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Document getDocument(String rsxmlx) {
        String rsxmlx1 = rsxmlx.replaceAll(">\\s+<", "><");
        System.out.println(rsxmlx1);
        Document document = null;
        try {
            document = DocumentHelper.parseText(rsxmlx1); //String转xml
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * @param args
     */
//    public static void main(String[] args) throws TException {
//        UserHandler tc = new UserHandler();
//        com.thrift.server.User user = new User();
//       // tc.addUser(user);
//        String mobile = "13822152569";
//        tc.getUserInfo(mobile);
//    }


}
