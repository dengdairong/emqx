package com.ddr.emqx.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;


/**
 * 对类的创建之前进行初始化的操作，在afterPropertiesSet()中完成。
 */
@Service
public class MqttClientManager implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(MqttClientManager.class);

    /**
     * mqtt连接配置
     */
    private final MqttConfig mqttConfig;

    private MqttConnectionPool<MqttConnection> mqttPool;

    public MqttClientManager(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    /**
     * 创建连接池
     */
    @Override
    public void afterPropertiesSet() {
        try {
            // 连接池配置
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            this.initPoolConfig(poolConfig);

            // mqtt连接配置
            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setUserName(this.mqttConfig.getUserName());
            if (StringUtils.hasText(mqttConfig.getPassword())) {
                connOpts.setPassword(this.mqttConfig.getPassword().getBytes());
            }

            // 创建工厂对象
            MqttConnectionFactory connectionFactory = new MqttConnectionFactory(mqttConfig.getHost(), connOpts);

            // 创建连接池
            mqttPool = new MqttConnectionPool<>(connectionFactory, poolConfig);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void initPoolConfig(GenericObjectPoolConfig poolConfig) {

        MqttPoolConfig mqttConnectionPoolConfig = this.mqttConfig.getPoolConfig();

        if (!Objects.isNull(mqttConnectionPoolConfig) && mqttConnectionPoolConfig.isCustomSet()) {

            // 设置连接池配置信息
            poolConfig.setMinIdle(mqttConnectionPoolConfig.getMinIdle());
            poolConfig.setMaxIdle(mqttConnectionPoolConfig.getMaxIdle());
            poolConfig.setMaxTotal(mqttConnectionPoolConfig.getMaxTotal());
            // TODO 补全

        }
    }

    /**
     * 根据key找到对应连接
     */
    public MqttConnection getConnection() throws Exception {
        return this.mqttPool.borrowObject();
    }

}

