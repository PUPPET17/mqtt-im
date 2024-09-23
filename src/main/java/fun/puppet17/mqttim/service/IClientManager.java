package fun.puppet17.mqttim.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author xyx
 * @date 2024/9/19
 */
public interface IClientManager {
    
    MqttClient createClientForUser(Integer userId) throws MqttException;
    
    MqttClient getMqttClient(Integer userId);
}
