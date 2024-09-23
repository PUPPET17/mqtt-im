package fun.puppet17.mqttim.controller;

import fun.puppet17.mqttim.service.IGroupChatService;
import jakarta.annotation.Resource;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PUPPET17
 */
@RestController
@RequestMapping("/group")
public class GroupChatController {
    
    @Resource
    private IGroupChatService groupChatService;
    
    // 订阅群组
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToGroup(@RequestParam Integer userId, @RequestParam Integer groupId) {
        try {
            groupChatService.subscribeToGroup(userId, groupId);
            return ResponseEntity.ok("用户 " + userId + " 成功订阅群组 " + groupId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("订阅失败: " + e.getMessage());
        }
    }
    
    // 发送消息到群组
    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToGroup(@RequestParam Integer userId,
                                                     @RequestParam Integer groupId,
                                                     @RequestParam String messageContent) {
        try {
            groupChatService.sendMessageToGroup(userId, groupId, messageContent);
            return ResponseEntity.ok("消息发送成功");
        } catch (MqttException e) {
            return ResponseEntity.status(500).body("发送失败: " + e.getMessage());
        }
    }
}
