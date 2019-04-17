/**
 * SynchInfo.java
 * com.timeson.oa.workWebservices.services
 * 2012-11-27
 * 上午10:30:39
 * Administrator
 * SynchInfo
 */
package common;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 上午10:30:39 WebService数据接口发布
 * 
 */
@WebService(name = "userSoapService", targetNamespace = WsConstants.NS)
public interface UserSoapService {
 
	/**
	 * 新接口 会员用户注册接口
	 **/
	public String register(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);

	/** 查询会员接口 **/
	public String userQuery(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);

	/** 会员用户信息修改接口 **/
	public String userUpdate(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);
	/**
	 * 发送短信接口
	 * @param token
	 * @param sign
	 * @param body
	 * @return
	 */
	public String sendTextMessages(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);
	
	public String productAuthenticitySelectWeb(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);
	/** 真伪查询接口CC **/
	public String productAuthenticitySelectCC(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);
	/** 真伪查询接口 **/
	public String productAuthenticitySelect(@WebParam(name = "TOKEN") String token, @WebParam(name = "SIGN") String sign, @WebParam(name = "BODY") String body);

	
}
