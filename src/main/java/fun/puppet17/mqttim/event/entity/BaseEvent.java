package fun.puppet17.mqttim.event.entity;

import lombok.Data;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Data
public abstract class BaseEvent {
    
    private Integer userId;
    
    public BaseEvent(Integer userId) {
        this.userId = userId;
    }
}
