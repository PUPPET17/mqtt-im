package fun.puppet17.mqttim.service;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author xyx
 * @date 2024/9/19
 */
public interface IGroupChatService {
    
    void sendMessageToGroup(Integer userId, Integer groupId, String messageContent) throws MqttException;
    
    void subscribeToGroup(Integer userId, Integer groupId) throws Exception;
    
}
