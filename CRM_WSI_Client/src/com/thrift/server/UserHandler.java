package com.thrift.server;

import common.GetTokenResult;
import common.WebServiceExecuterNew;
import org.apache.thrift.TException;
import org.springside.modules.mapper.JaxbMapper;


public class UserHandler implements com.thrift.server.AyService.Iface{
    public static String url = "http://61.186.97.141:1211/wsi";
    public static String spPassword = "f929a717f99cbdd94eaa499207b86404";
    public static String spName = "b92705ab6294a656f8e073d6503bac55";
    public static String key = "c9d4cce1a2014d3bba0e1d434ee2e5e7";
    public static Integer company_id = 6;

    public static String rsxml = WebServiceExecuterNew.getWsiSoapService(url, spName, spPassword).generationToken();
    public static GetTokenResult result = JaxbMapper.fromXml(rsxml, GetTokenResult.class);

    public int addUser(com.thrift.server.User user) throws TException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo><companyId>1</companyId><userName>13822152569</userName></userQueryInfo>";
        return 0;
    }


    public User getUserInfo(String mobile){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo><companyId>1</companyId><userName>13822152569</userName></userQueryInfo>";
        return null;
    }

}
