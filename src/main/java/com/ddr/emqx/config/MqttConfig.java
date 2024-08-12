package com.ddr.emqx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(MqttConfig.class)
@ConfigurationProperties(prefix = "mqtt")
@Component
public class MqttConfig {
    /**
     * MQTT host 地址
     */
    private String host;

    /**
     * 客户端Id
     */
    private String clientId;

    /**
     * 登录用户(可选)
     */
    private String userName;

    /**
     * 登录密码(可选)
     */
    private String password;
 
    /**
     * Mqtt Pool Config
     */
    private MqttPoolConfig poolConfig;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MqttPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(MqttPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

}

