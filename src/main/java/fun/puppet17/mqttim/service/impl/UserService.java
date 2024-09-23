package fun.puppet17.mqttim.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.puppet17.mqttim.domain.po.UserInfo;
import fun.puppet17.mqttim.domain.req.AuthReq;
import fun.puppet17.mqttim.domain.res.AuthRes;
import fun.puppet17.mqttim.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Service
@Slf4j
public class UserService implements IUserService {
    
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    
    private final ObjectMapper objectMapper;
    
    public UserService(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void register(AuthReq authReq) throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(authReq,userInfo);
        String jsonString = objectMapper.writeValueAsString(userInfo);
        // 暂时按照clientid当key
        redisTemplate.opsForValue().set(authReq.getClientid(), jsonString);
        // 当前AclEntry为空需要后续订阅相应的topic时再赋值
    }
    
    @Override
    public AuthRes authentication(AuthReq authReq) throws JsonProcessingException {
//        String jsonString = (String) redisTemplate.opsForValue().get(authReq.getClientid());
//        UserInfo userInfo = objectMapper.readValue(jsonString, UserInfo.class);
//        if (userInfo == null) {
//            return null;
//        }
//        if (userInfo.getAclEntry().getTopic()==null) {
//            log.info("请订阅topic后重试");
//            return null;
//        }
//        userInfo.
        return null;
    }
}
