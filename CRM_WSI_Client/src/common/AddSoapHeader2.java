package common;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @Title:在发送消息前，封装Soap Header 信息
 * 
 * @Description:
 * 
 * @Copyright:
 *
 * @author zz
 * @version 1.00.000
 *
 */

public class AddSoapHeader2 extends AbstractSoapInterceptor { 
	String spPassword = "";
	String spName = "";

	public AddSoapHeader2() {
		super(Phase.WRITE);
	}
	public AddSoapHeader2(String spName, String spPassword) {
		super(Phase.WRITE);
		this.spName = spName;
		this.spPassword = spPassword;

	}

	public void handleMessage(SoapMessage message) throws Fault {
//		String spPassword = "af00c9df3fba93bfe450580b6474073f";		
//		String spName = "b0bd041248265d0d3de559515ca563bf";
		
//		String spPassword = "b074acb04e0f7dc3";
//		String spName = "210363474135c870";

		QName qname = new QName("RequestSOAPHeader");
		Document doc = DOMUtils.createDocument();
		// 自定义节点
		Element spId = doc.createElement("user_secret_key1");
		spId.setTextContent(spName);
		// 自定义节点
		Element spPass = doc.createElement("user_secret_key2");
		spPass.setTextContent(spPassword);

		Element root = doc.createElementNS(WsConstants.NS, "RequestSOAPHeader");
		root.appendChild(spId);
		root.appendChild(spPass);

		SoapHeader head = new SoapHeader(qname, root);
		List<Header> headers = message.getHeaders();
		headers.add(head);
		//System.out.println(">>>>>添加header<<<<<<<");
	}

}