package common;

import java.util.ArrayList;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * 新接口调用类
 * 
 * @author Administrator
 *
 */
public class WebServiceExecuterNew {
	
	public static WsiSoapService getWsiSoapService(String url, String spName, String spPassword) {
		WsiSoapService rv = null;

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		ArrayList<Interceptor<?>> list = new ArrayList<Interceptor<?>>();
		list.add(new AddSoapHeader2(spName, spPassword));
		list.add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
		factory.setOutInterceptors(list);
		factory.setServiceClass(WsiSoapService.class);
		factory.setAddress(url + "/cxf/soap/wsiservice?wsdl");

		Object obj = factory.create();
		if (obj != null) {
			rv = (WsiSoapService) obj;
		}

		return rv;
	}

	/**
	 * 获取用户服务
	 * 
	 * @return
	 */
	public static UserSoapService getUserSoapService(String url, String spName, String spPassword) {
		UserSoapService rv = null;

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		ArrayList<Interceptor<?>> list = new ArrayList<Interceptor<?>>();
		list.add(new AddSoapHeader2(spName, spPassword));
		list.add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
		factory.setOutInterceptors(list);
		factory.setServiceClass(UserSoapService.class);
		factory.setAddress(url + "/cxf/soap/userService?wsdl");

		Object obj = factory.create();
		if (obj != null) {
			rv = (UserSoapService) obj;
		}

		return rv;
	}

}
