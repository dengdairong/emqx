package com.ddr.emqx;

import com.ddr.emqx.config.MqttClientManager;
import com.ddr.emqx.config.MqttConnection;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AppRunner implements CommandLineRunner {
    @Autowired
    private MqttClientManager manager;

    @Override
    public void run(String... args) throws Exception {
        MqttConnection conn = manager.getConnection();
        conn.subscribe("testtopic/#", new MqttMessageListener());
        conn.subscribe("testtopic1/#", (topic, message) -> {
            log.info("topic: " + topic + " message: " + message.toString());
        });
    }
}
