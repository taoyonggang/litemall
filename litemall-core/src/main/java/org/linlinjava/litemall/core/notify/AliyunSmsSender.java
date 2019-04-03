package org.linlinjava.litemall.core.notify;

import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.config.NotifyProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 腾讯云短信服务
 */
public class AliyunSmsSender implements SmsSender {
    private final Log logger = LogFactory.getLog(AliyunSmsSender.class);

/*    SmsClient smsClient = new SmsClient(accessKeyId, accessKeySecret);
    SmsTemplate smsTemplate = SmsTemplate.builder()
            .signName(signName)
            .templateCode(templateCode)
            .addTemplateParam("code", "123456")
            .phoneNumbers(phoneNumber)
            .build();
   smsClient.send(smsTemplate);*/

    private SmsClient sender;

    private String signName;

    public void setSender(SmsClient sender,String signName) {
        this.sender = sender;
        this.signName = signName;
    }

    @Override
    public SmsResult send(String phone, String content) {
        try {
            List<String> phones = new ArrayList<String>();
            phones.add(phone);
            SmsTemplate smsTemplate = SmsTemplate.builder()
                    .signName(signName)
                    .addTemplateParam("code", content)
                    .phoneNumbers(phones)
                    .build();
            sender.send(smsTemplate);
            SmsResult result = new SmsResult();
            result.setSuccessful(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SmsResult sendWithTemplate(String phone, String templateId, String[] params) {
        try {
            List<String> phones = new ArrayList<String>();
            phones.add(phone);
            SmsTemplate smsTemplate = SmsTemplate.builder()
                    .signName(signName)
                    .templateCode(templateId)
                    .addTemplateParam("code", params[0])
                    .phoneNumbers(phones)
                    .build();
            sender.send(smsTemplate);
            SmsResult result = new SmsResult();
            result.setSuccessful(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
