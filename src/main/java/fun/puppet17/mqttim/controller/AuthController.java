package fun.puppet17.mqttim.controller;

import fun.puppet17.mqttim.domain.req.AuthReq;
import fun.puppet17.mqttim.domain.req.AuthorizationReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/authentication")
    public ResponseEntity<Map<String, String>> authentication(
            @RequestBody AuthReq authReq) {
        log.info("认证信息:"+authReq.toString());
        if (true) {
            Map<String, String> response = new HashMap<>();
            response.put("result", "allow");
            return ResponseEntity.ok(response);
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("result", "deny");
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    
    @PostMapping("/acl")
    public ResponseEntity<Map<String,String>> authorization(
            @RequestBody AuthorizationReq authorizationReq){
        log.info("授权请求"+authorizationReq.toString());
        if (true){
            Map<String, String> response = new HashMap<>();
            response.put("result", "true");
            return ResponseEntity.ok(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("result", "false");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    
    public ResponseEntity<Map<String,String>> register(@RequestBody AuthReq authReq){
        return null;
    }

}
