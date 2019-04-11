package org.linlinjava.litemall.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qrcode")
public class QrcodeProperties {
    private String codeurl;

    private String imgageurl;

    public String getCodeurl() {
        return codeurl;
    }

    public void setCodeurl(String codeurl) {
        this.codeurl = codeurl;
    }

    public String getImgageurl() {
        return imgageurl;
    }

    public void setImgageurl(String imgageurl) {
        this.imgageurl = imgageurl;
    }


}
