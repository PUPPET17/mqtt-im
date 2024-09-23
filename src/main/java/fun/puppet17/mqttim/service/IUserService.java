package fun.puppet17.mqttim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fun.puppet17.mqttim.domain.req.AuthReq;
import fun.puppet17.mqttim.domain.res.AuthRes;

/**
 * @author xyx
 * @date 2024/9/20
 */
public interface IUserService {
    
    void register(AuthReq authReq) throws JsonProcessingException;
    
    AuthRes authentication(AuthReq authReq) throws JsonProcessingException;
}
