package com.ddr.emqx.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class MqttUtil {

    @Autowired
    MqttClientManager mqttManager;

    public void publish(String topic, String message) {
        log.info("publish INFO ; clientId={}, message={}", topic, message);
        MqttConnection connection = null;
        try {
            connection = mqttManager.getConnection();
            log.info("publish INFO ; clientId={},targetUrl={}", topic, connection.getMqttClient().getServerURI());
            connection.getMqttClient().setCallback(new PushCallback());
            connection.publish(topic, message);
        } catch (Exception e) {
            log.error("publish ERROR : topic={},message={}", topic, message, e, e);
        } finally {
            if (null != connection) {
                connection.close();
            }
        }
    }
}

