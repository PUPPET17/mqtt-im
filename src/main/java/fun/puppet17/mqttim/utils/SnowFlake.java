package fun.puppet17.mqttim.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author xyx
 * @date 2024/9/25
 */
@Component
public class SnowFlake {
    
    private Snowflake snowflake;
    
    @PostConstruct
    public void init() {
        // 0 ~ 31 位，可以采用配置的方式使用
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
        
        workerId = workerId >> 16 & 31;
        
        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
    }
    
    public synchronized long nextId() {
        return snowflake.nextId();
    }
    
}