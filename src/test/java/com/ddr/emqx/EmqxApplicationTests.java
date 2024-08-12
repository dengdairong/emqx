package com.ddr.emqx;

import com.ddr.emqx.config.MqttUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmqxApplicationTests {
    @Autowired
    private MqttUtil mqttUtil;

    @Test
    void contextLoads() {
        mqttUtil.publish("testtopic/ddr", "test");
    }

}
