package com.thrift.server;

import java.security.Key;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;
import org.springside.modules.mapper.JaxbMapper;

import common.Coder;
import common.DESCoder;
import common.GetTokenResult;
import common.WebServiceExecuterNew;
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

	public static UserHandler handler;
	public static AyService.Processor processor;

	/**
	 * 测试1
	 */
	private String getXml_encrypt(String key, String xml) throws Exception {

		Key k = DESCoder.toKey(key.getBytes());
		byte[] encryptData = DESCoder.encrypt(xml.getBytes(), k);
		String xml_encrypt = new BASE64Encoder().encode(encryptData); // 加密后的数据
		return xml_encrypt;

	}

	public void testSend1() {
		try {

			String url = "http://61.186.97.141:1211/wsi";
			String spPassword = "f929a717f99cbdd94eaa499207b86404";
			String spName = "b92705ab6294a656f8e073d6503bac55";
			String key = "c9d4cce1a2014d3bba0e1d434ee2e5e7";

			String rsxml = WebServiceExecuterNew.getWsiSoapService(url, spName, spPassword).generationToken();
			System.out.println(rsxml);
			GetTokenResult result = JaxbMapper.fromXml(rsxml, GetTokenResult.class);

			System.out.println(result.getToken()); // 缓存起来.不需要每次都调用令牌
			System.out.println(result.getInvalidDate()); // 缓存起来.不需要每次都调用令牌

		//	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo><companyId>1</companyId><userName>13000011111</userName></userQueryInfo>";
			// String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><REQUEST>" +
			// " <COMPANY_ID>1</COMPANY_ID>" +
			// " <MOBILE_NO>18163629257</MOBILE_NO>" +
			// " <CONTENTS>你好</CONTENTS>" +
			// " <SUBJECT>主题</SUBJECT>" +
			// " <IS_REALTIME_MSG>N</IS_REALTIME_MSG>" +
			// "</REQUEST>";
		//	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><productInfo><companyId>1</companyId><logisticsId>00100152646302662209</logisticsId></productInfo>";
 	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><userQueryInfo><companyId>1</companyId><userName>13822152569</userName></userQueryInfo>";
			
			String xml_encrypt = getXml_encrypt(key, xml);
			String privateKey = IOUtils.toString(AyServiceRun.class.getResourceAsStream("/key/privateKey.txt"));

			String sign = Coder.sign(xml_encrypt.getBytes(), privateKey); // 加密后的数据进行签名

			System.out.println("xml_encrypt:" + xml_encrypt);
			System.out.println("----------");
			System.out.println("sign:" + sign);

			String rsxmlx = WebServiceExecuterNew.getUserSoapService(url, spName, spPassword).userQuery(result.getToken(), sign, xml_encrypt);

			System.out.println(rsxmlx);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
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

			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}