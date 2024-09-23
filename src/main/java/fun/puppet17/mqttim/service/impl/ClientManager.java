package fun.puppet17.mqttim.service.impl;


import fun.puppet17.mqttim.service.IClientManager;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Service
public class ClientManager implements IClientManager {
    
    private static final String BROKER_URL = "tcp://117.72.16.190:1883";
    
    private final ConcurrentHashMap<Integer, MqttClient> userClients = new ConcurrentHashMap<>();
    
    @Override
    public MqttClient createClientForUser(Integer userId) throws MqttException {
        String clientId = "client_" + userId;
        MqttClient mqttClient = new MqttClient(BROKER_URL, clientId, new MemoryPersistence());
        
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        
        mqttClient.connect(options);
        userClients.put(userId, mqttClient);
        
        return mqttClient;
    }
    
    @Override
    public MqttClient getMqttClient(Integer userId) {
        return userClients.get(userId);
    }
    
}
