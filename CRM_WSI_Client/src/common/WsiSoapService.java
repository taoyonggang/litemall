/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package common;

import javax.jws.WebService;

/**
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义. 使用WSResult及其子类类包裹返回结果. 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * @author calvin
 */
// name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "WsiSoapService", targetNamespace = WsConstants.NS)
public interface WsiSoapService {
	/**
	 * 获取令牌
	 * 
	 * @Title: generationToken
	 * @param
	 * @return GetTokenResult
	 * @throws @DATE
	 *             2016年3月30日下午5:25:49
	 * @author liangyi
	 */
	String generationToken();
  

}
