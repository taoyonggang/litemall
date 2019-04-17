package org.linlinjava.litemall.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "litemall.crm")
public class CrmProperties {

    private Boolean enalbe;

    private String host;

    private Integer port;

    private Integer timeout;

    public Boolean getEnalbe() {
        return enalbe;
    }

    public void setEnalbe(Boolean enalbe) {
        this.enalbe = enalbe;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
