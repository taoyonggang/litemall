package org.linlinjava.litemall.core.system;

import org.apache.commons.codec.digest.DigestUtils;
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

    @Autowired
    private CrmProperties properties;

    private Boolean isConnected = false;
    private TTransport transport = null;
    private AyService.Client client = null;

    @PostConstruct
    public Boolean start(){
        if (transport!=null&&client!=null) return true;
        isConnected = false;
        try {
            transport = new TSocket(properties.getHost(), properties.getPort(),properties.getTimeout() );
            TProtocol protocol = new TCompactProtocol(transport);
            client = new AyService.Client(protocol);
            transport.open();
            isConnected = true;
            return isConnected;
        } catch (TTransportException e) {
            e.printStackTrace();
            transport = null;
            client = null;
        } catch (TException e) {
            e.printStackTrace();
            transport = null;
            client = null;
        } finally {
            System.out.println("rpc run...");
        }
        return isConnected;
    }
    @PreDestroy
    public void stop(){
        if (checkStarted()){
            client = null;
            transport.close();
            transport = null;
            isConnected = false;
        }
    }
    public int addUser(LitemallUser user,String codes){
        if (checkStarted()&&codes!=null&&codes.length()>10){
            User crm_user = new User();
            crm_user.setMemberUsername(user.getMemberUsername());
            crm_user.setMobile(user.getMobile());
            if (user.getPassword()==null) user.setPassword("123456");
            String md5Hex = DigestUtils
                    .md5Hex(user.getPassword()).toUpperCase();
            crm_user.setPassword(md5Hex);
            crm_user.setBabybirthday(DateTimeUtil.getDateDisplayString2(user.getBabybirthday()));
            crm_user.setBabybirthday2(DateTimeUtil.getDateDisplayString2(user.getBabybirthday2()));
            crm_user.setBabysex(user.getBabysex());
            crm_user.setBabysex2(user.getBabysex2());
            crm_user.setAddress(user.getAddress());
            crm_user.setCompany_id(6);
            crm_user.setOrigin("weixin_origin");
            crm_user.setUser_type("member_user");
            String[] code = codes.split(" ");
            if (code!=null&&code.length >=3){
                crm_user.setProvince(code[0]);
                crm_user.setCity(code[1]);
                crm_user.setArea(code[2]);
            }
            try {
                return client.addUser(crm_user);
            }catch (Exception e) {
                e.printStackTrace();
                client = null;
                transport = null;
                isConnected = false;
            }
        }
        return -1;
    };

    public LitemallUser getUser(String mobile) {
        if (checkStarted()){
            try {
                User crm_user = client.getUserInfo(mobile);
                if (crm_user!=null&&crm_user.getMobile()!=null){
                    LitemallUser user = new LitemallUser();
                    user.setMemberUsername(crm_user.getMemberUsername());
                    user.setIntegral(crm_user.getIntegral());
                    return user;
                }
            }catch (Exception e) {
                e.printStackTrace();
                client = null;
                transport = null;
                isConnected = false;
            }
        }
        return null;
    }

    private Boolean checkStarted(){
        if (isConnected&&transport!=null&&client!=null&&transport.isOpen())
            return true;
        else return start();
    }
}
