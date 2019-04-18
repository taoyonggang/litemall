package com.thrift.server;

import java.security.Key;
import java.util.logging.Logger;


import common.Coder;
import common.GetTokenResult;
import common.WebServiceExecuterNew;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;
import common.DESCoder;
import org.springside.modules.mapper.JaxbMapper;
import sun.misc.BASE64Encoder;

/**
 * @Title:
 * 
 * @Description:
 * 
 * @Copyright:
 * 
 * @author ly
 * @version 1.00.000
 * 
 */
public class AyServiceRun {

	public static String url = "http://wsapi.ausnutria.com:1111/wsi";
	public static String spPassword = "bff7bc472d0bfc9c024fdd10751b355d";
	public static String spName = "b92705ab6294a656f8e073d6503bac55";
	public static String key = "e602df4dc47e41bdaea209d68b66fa45";

	public static String rsxml = null;
	public static GetTokenResult result = null;
	public static String xml_encrypt = null;
	public static String privateKey = null;
	public static String sign = null; // 加密后的数据进行签名

	public static UserHandler handler;
	public static AyService.Processor processor;

	/**
	 * 测试1
	 */
	private static String getXml_encrypt(String key, String xml) throws Exception {

		Key k = DESCoder.toKey(key.getBytes());
		byte[] encryptData = DESCoder.encrypt(xml.getBytes(), k);
		String xml_encrypt = new BASE64Encoder().encode(encryptData); // 加密后的数据
		return xml_encrypt;

	}

	public static Boolean init(){
		try {
			rsxml = WebServiceExecuterNew.getWsiSoapService(url, spName, spPassword).generationToken();
			result = JaxbMapper.fromXml(rsxml, GetTokenResult.class);
			privateKey = IOUtils.toString(UserHandler.class.getResourceAsStream("/key/privateKey.txt"));
			return true;
		}catch(Exception e){
			return false;
		}

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (!AyServiceRun.init()){
				System.out.println("CRM Servcie connect failed.");
				return;
			}
			handler = new UserHandler();
			processor = new AyService.Processor(handler);

			Runnable simple = new Runnable() {
				public void run() {
					simple(processor);
				}
			};
			new Thread(simple).start();

		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public static void simple(AyService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);

            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            AyService.Processor<AyService.Iface> tprocessor = new AyService.Processor<AyService.Iface>(new UserHandler());
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TCompactProtocol.Factory());
			TServer server = new TThreadPoolServer(tArgs);

			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}