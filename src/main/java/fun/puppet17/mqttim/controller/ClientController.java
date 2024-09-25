package fun.puppet17.mqttim.controller;

import fun.puppet17.mqttim.service.IQRCodeGenerator;
import fun.puppet17.mqttim.service.impl.ClientManager;
import fun.puppet17.mqttim.utils.QRGenerator;
import fun.puppet17.mqttim.utils.SnowFlake;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Resource
    private IQRCodeGenerator iqrCodeGenerator;
    
    @Resource
    private SnowFlake snowFlake;
    
    @Resource
    private ClientManager clientManager;
    
    private static final Set<MqttClient> mqttClientSet = new HashSet<>();
    
    @GetMapping("/qr")
    public ResponseEntity<String> getQr(){
        String uniqueFileName = String.valueOf(snowFlake.nextId());
        byte[] imageByte = QRGenerator.generateImageByte("www.puppet17.fun", 300, 300);
        String qrCodeUrl = iqrCodeGenerator.uploadQrCodeToMinio(imageByte, uniqueFileName);
        log.info("uniqueFileName:{},qr-url:{}",uniqueFileName,qrCodeUrl);
        return ResponseEntity.ok(qrCodeUrl);
    }
    
    @GetMapping("/push")
    public ResponseEntity<String> push(@RequestParam(value = "userId", defaultValue = "1") int userId) {
        try {
            clientManager.createClientForUser(userId);
            MqttClient mqttClient = clientManager.getMqttClient(userId);
            byte[] imageByte = QRGenerator.generateImageByte("www.puppet17.fun", 300, 300);
            mqttClient.publish("/qr", imageByte, 2, true);
            
            return ResponseEntity.ok("QR code pushed successfully.");
        } catch (MqttException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to push QR code: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

}
