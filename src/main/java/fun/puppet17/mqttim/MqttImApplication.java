package fun.puppet17.mqttim;

import okhttp3.*;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author PUPPET17
 */
@SpringBootApplication
public class MqttImApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MqttImApplication.class, args);
        System.out.println("SpringBoot,启动!");
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
}
