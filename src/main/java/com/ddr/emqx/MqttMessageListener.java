package com.ddr.emqx;

import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.mqttv5.client.IMqttMessageListener;
import org.eclipse.paho.mqttv5.common.MqttMessage;

@Log4j2
public class MqttMessageListener implements IMqttMessageListener {
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("topic:{}, message:{}", topic, message);
    }
}
