package com.thrift.server;

import java.security.Key;

import org.apache.commons.io.IOUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
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