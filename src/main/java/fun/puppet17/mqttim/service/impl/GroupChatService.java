package fun.puppet17.mqttim.service.impl;

import fun.puppet17.mqttim.service.IGroupChatService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Slf4j
@Service
public class GroupChatService implements IGroupChatService {
    
    @Resource
    private final ClientManager clientManager;
    
    public GroupChatService(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Override
    public void subscribeToGroup(Integer userId, Integer groupId) throws Exception {
        MqttClient mqttClient = clientManager.getMqttClient(userId);
        if (mqttClient != null) {
            String topic = "group/" + groupId;  // 定义群组的主题
            mqttClient.subscribe(topic);  // 用户独立订阅群组
            
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    log.info("连接丢失：" + cause.getMessage());
                }
                
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    log.info("用户 " + userId + " 接收到消息：群组 - " + topic + "，内容 - " + new String(message.getPayload()));
                }
                
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    log.info("消息发送完成");
                }
            });
        }
    }
    
    @Override
    public void sendMessageToGroup(Integer userId, Integer groupId, String messageContent) throws MqttException {
        MqttClient mqttClient = clientManager.getMqttClient(userId);
        if (mqttClient != null) {
            String topic = "group/" + groupId;
            MqttMessage message = new MqttMessage(messageContent.getBytes());
            message.setQos(2);
            mqttClient.publish(topic, message);
            log.info("用户 " + userId + " 发送消息到群组 " + groupId + "，内容：" + messageContent);
        }
    }
    
}

