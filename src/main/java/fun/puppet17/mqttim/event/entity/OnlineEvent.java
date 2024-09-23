package fun.puppet17.mqttim.event.entity;

/**
 * @author xyx
 * @date 2024/9/19
 */
public class OnlineEvent extends BaseEvent{
    public OnlineEvent(Integer userId) {
        super(userId);
    }
}
