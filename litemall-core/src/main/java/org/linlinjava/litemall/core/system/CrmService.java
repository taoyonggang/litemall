package org.linlinjava.litemall.core.system;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.com.thrift.server.AyService;
import org.com.thrift.server.User;
import org.linlinjava.litemall.core.config.CrmProperties;
import org.linlinjava.litemall.core.config.WxProperties;
import org.linlinjava.litemall.core.util.DateTimeUtil;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype")
public class CrmService {
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private CrmProperties properties;

    private static Boolean isConnected = false;
    private TTransport transport = null;
    private AyService.Client client = null;

    @PostConstruct
    public Boolean start(){
        //if (isConnected&&transport!=null&&client!=null&&transport.isOpen()) return true;
        isConnected = false;
        try {
            logger.info("CRM客户端开始连接.....");
            transport = new TSocket(properties.getHost(), properties.getPort(),properties.getTimeout() );
            TProtocol protocol = new TCompactProtocol(transport);
            client = new AyService.Client(protocol);
            transport.open();
            isConnected = true;
            logger.info("CRM客户端连接成功");
            return isConnected;
        } catch (TTransportException e) {
            logger.error(e.getMessage(),e);
            transport = null;
            client = null;
        } catch (TException e) {
            logger.error(e.getMessage(),e);
            transport = null;
            client = null;
        }
        logger.info("CRM客户端连接失败");
        return isConnected;
    }
    @PreDestroy
    public void stop(){

        if (checkStarted()){
            client = null;
            transport.close();
            transport = null;
            isConnected = false;
            logger.info("CRM客户端断开连接");
        }
    }
    public int addUser(LitemallUser user,String codes){
        logger.info("添加CRM用户");
        if (checkStarted()&&codes!=null&&codes.length()>10){
            try {
                logger.info("开始添加CRM用户");
                User crm_user = new User();
                crm_user.setMemberUsername(user.getMemberUsername());
                crm_user.setMobile(user.getMobile());
                if (user.getPassword()==null) user.setPassword("123456");
                    String md5Hex = DigestUtils
                            .md5Hex(user.getPassword()).toUpperCase();
                crm_user.setPassword(md5Hex);
                crm_user.setBabybirthday(DateTimeUtil.getDateDisplayString2(user.getBabybirthday()));
                crm_user.setBabybirthday2(DateTimeUtil.getDateDisplayString2(user.getBabybirthday2()));

                crm_user.setAddress(user.getAddress());
                crm_user.setCompany_id(6);
                crm_user.setOrigin("weixin_origin");
                crm_user.setUser_type("member_user");
                crm_user.setWeixinOpenid(user.getWeixinOpenid());
                crm_user.setAvatar(properties.getFrom()+"#"+user.getFromsouce());
                String[] code = codes.split(" ");
                if (code!=null&&code.length >=3){
                    crm_user.setProvince(code[0]);
                    crm_user.setCity(code[1]);
                    crm_user.setArea(code[2]);
                }
                byte sex1 = user.getBabysex();
                sex1 -= 1;
                byte sex2 = user.getBabysex();
                sex2 -= 1;
                crm_user.setBabysex(sex1);
                crm_user.setBabysex2(sex2);
                logger.info("结束添加CRM用户");
                return client.addUser(crm_user);
            }catch (Exception e) {
                logger.error(e.getMessage(),e);
                client = null;
                transport = null;
                isConnected = false;
            }
        }
        return -1;
    };

    public LitemallUser getUser(String mobile) {
        logger.info("获取CRM用户");
        if (checkStarted()){
            try {
                logger.info("开始获取CRM用户");
                User crm_user = client.getUserInfo(mobile);
                if (crm_user!=null&&crm_user.getMobile()!=null){
                    LitemallUser user = new LitemallUser();
                    user.setMemberUsername(crm_user.getMemberUsername());
                    user.setIntegral(crm_user.getIntegral());
                    user.setGrade(crm_user.getGrade());
                    logger.info("成功获取CRM用户");
                    return user;
                }
            }catch (Exception e) {
                logger.error(e.getMessage(),e);
                client = null;
                transport = null;
                isConnected = false;
            }
        }
        logger.info("获取CRM用户失败");
        return null;
    }

    private Boolean checkStarted(){
        if (isConnected&&transport!=null&&client!=null&&transport.isOpen()){
            logger.info("CRM客户端已经连接，直接返回");
            return true;
        }
        else {
            logger.info("CRM客户端未连接，尝试重连一次");
            return start();
        }
    }
}
