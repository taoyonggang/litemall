package com.thrift.server;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Properties;
import java.util.Timer;
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

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AyServiceRun.class.getName());

	public static String url = "";
	public static String spPassword = "";
	public static String spName = "";
	public static String key = "";

	public static String rsxml = null;
	public static GetTokenResult result = null;
	public static String privateKey = null;

	public static UserHandler handler;
	public static AyService.Processor processor;

    /**
     * 读取配置文件
     */
	private static void readProperties(){
	    logger.info("读取配置文件");
        InputStream inStream = UserHandler.class.getClassLoader().getResourceAsStream("code.properties");
        Properties properties = new Properties();
            try {
                properties.load(inStream);
                logger.info("加载配置");
                if (properties.get("url")!=null)
                    url = properties.get("url").toString();
                if (properties.get("spPassword")!=null)
                    spPassword = properties.get("spPassword").toString();
                if (properties.get("spName")!=null)
                    spName = properties.get("spName").toString();
                if (properties.get("key")!=null)
                    key = properties.get("key").toString();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
	}


    /**
     * 初始化连接 SOA服务器，获取token
     * @return
     */
	public static Boolean init(){
		try {
			rsxml = WebServiceExecuterNew.getWsiSoapService(url, spName, spPassword).generationToken();
			result = JaxbMapper.fromXml(rsxml, GetTokenResult.class);
			privateKey = IOUtils.toString(UserHandler.class.getResourceAsStream("/key/privateKey.txt"));
			return true;
		}catch(Exception e){
            logger.error(e.getMessage(),e);
            return false;
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

            readProperties();

			if (!AyServiceRun.init()){
                logger.error("CRM Servcie connect failed.");
				return;
			}

            logger.info("开启定时刷新token任务，没有加锁");
			Timer timer = new Timer();
			//每12小时刷新token
			timer.schedule(new TimerTaskRefresh(), 1000*60*60*12, 1000*60*60*12);

			handler = new UserHandler();
			processor = new AyService.Processor(handler);

			Runnable SOAServer = new Runnable() {
				public void run() {
                    SOAServer(processor);
				}
			};
            logger.info("开始启动业务处理RPC服务器");
			new Thread(SOAServer).start();

		} catch (Exception e) {
            logger.error(e.getMessage(),e);
		}
	}

	public static void SOAServer(AyService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);

            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            AyService.Processor<AyService.Iface> tprocessor = new AyService.Processor<AyService.Iface>(new UserHandler());
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TCompactProtocol.Factory());
			TServer server = new TThreadPoolServer(tArgs);

            logger.info("服务器启动完成,开始提供服务.....");
			server.serve();
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
		}
	}
}
