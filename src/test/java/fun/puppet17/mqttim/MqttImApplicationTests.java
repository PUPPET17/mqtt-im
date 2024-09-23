package fun.puppet17.mqttim;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MqttImApplicationTests {
    
    @Test
    void contextLoads() {
    }
    
    public static void v5_api() {
        try {
            String api_key = "ce1a4e3c4e997efe";
            String Secret_key = "jdpToNphJvgtOfb2RY78YV357TC9BhRhY7e9AgShLPQhM";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://localhost:18083/api/v5/clients/kickout/bulk")
                    .header("Content-Type", "application/json")
                    .header("Authorization", Credentials.basic(api_key, Secret_key))
                    .build();

            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sub() {
        String broker = "tcp://localhost:1883";
        String topic = "device/data/sensor1";
        String clientId = "BusinessServiceClient";
        
        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.connect();
            
            client.subscribe(topic, (topic1, message) -> {
                System.out.println("Received message: " + new String(message.getPayload()));
            });
            
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
}
