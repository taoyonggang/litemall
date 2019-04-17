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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;


import java.io.IOException;
import java.security.Key;


public class UserHandler implements com.thrift.server.AyService.Iface{
    public static String url = "http://61.186.97.141:1211/wsi";
    public static String spPassword = "f929a717f99cbdd94eaa499207b86404";
    public static String spName = "b92705ab6294a656f8e073d6503bac55";
    public static String key = "c9d4cce1a2014d3bba0e1d434ee2e5e7";
    public static Integer company_id = 6;
    public static String user_type = "member_user";
    public static String origin = "www.1897.com";

    public static String rsxml = WebServiceExecuterNew.getWsiSoapService(url, spName, spPassword).generationToken();
    public static GetTokenResult result = JaxbMapper.fromXml(rsxml, GetTokenResult.class);
    public static String xml_encrypt = null;
    public static String privateKey = null;
    public static String sign = null; // 加密后的数据进行签名
    private String getXml_encrypt(String key, String xml) throws Exception {

        Key k = DESCoder.toKey(key.getBytes());
        byte[] encryptData = DESCoder.encrypt(xml.getBytes(), k);
        String xml_encrypt = new BASE64Encoder().encode(encryptData); // 加密后的数据
        return xml_encrypt;

    }
    //
    public int addUser(com.thrift.server.User user) throws TException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<userInfo>" +
                "<company_id>"+company_id+"</company_id>" +
                "<mobiletel>"+user.getMobile()+"“”“”“”“”</mobiletel>" +
                "<open_id>"+user.getWeixinOpenid()+"</open_id>" +
                "<password>"+user.getPassword()+"</password>" +
                "<user_type></user_type>" +
                "<area>511722</area>" +
                "<city>511700</city>" +
                "<main_babay_birthday>20151225</main_babay_birthday>" +
                "<member_name>潘婷</member_name>" +
                "<member_no>"+user.getMobile()+"</member_no>" +
                "<province>"+user.getMobile()+"</province>" +
                "<referrer></referrer>" +
                "<address>叶家坡</address>" +
                "<origin>weixin_origin</origin>" +
                "<post_code>636150</post_code>" +
                "</userInfo>";

        try {
            xml_encrypt = getXml_encrypt(key, xml);
            privateKey = IOUtils.toString(UserHandler.class.getResourceAsStream("/key/privateKey.txt"));
            sign = Coder.sign(xml_encrypt.getBytes(), privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        String rsxmlx = WebServiceExecuterNew.getUserSoapService(url, spName, spPassword).register(result.getToken(), sign, xml_encrypt);
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
            if("memberId".equals(key)){
                System.out.println("存在memberId,值为:"+(int)map.get(key));
                return (int)map.get(key);
            }else {
                System.out.println("不存在memberId,返回0");
                return 0;
            }
        }
        return 0;
    }


    public User getUserInfo(String mobile){
        User user = new User();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo>><companyId>1</companyId><userName>13822152569</userName></userQueryInfo>";
        try {
            xml_encrypt = getXml_encrypt(key, xml);
            privateKey = IOUtils.toString(UserHandler.class.getResourceAsStream("/key/privateKey.txt"));
            sign = Coder.sign(xml_encrypt.getBytes(), privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
        String rsxmlx = WebServiceExecuterNew.getUserSoapService(url, spName, spPassword).userQuery(result.getToken(), sign, xml_encrypt);
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
                user.setIntegral((int) map.get(key));
            }else if("tier_id".equals(key)){
                user.setGrade((int) map.get(key));
            }else if("main_babay_birthday".equals(key)){
                user.setBabybirthday((String) map.get(key));
            }else if("main_babay_sex".equals(key)){
                user.setBabysex((byte) map.get(key));
            }else if("member_name".equals(key)){
                user.setMemberUsername((String) map.get(key));
            }
        }
        return user;
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
    public static void main(String[] args) throws TException {
        UserHandler tc = new UserHandler();
        com.thrift.server.User user = new User();
       // tc.addUser(user);
        String mobile = "13822152569";
        tc.getUserInfo(mobile);
    }


}
