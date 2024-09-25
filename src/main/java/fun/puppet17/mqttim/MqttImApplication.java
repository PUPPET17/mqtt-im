package fun.puppet17.mqttim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author PUPPET17
 */
@SpringBootApplication
public class MqttImApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MqttImApplication.class);
        app.setAdditionalProfiles("debug");
        app.run(args);
        System.out.println("SpringBoot,启动!");
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
}
