package fun.puppet17.mqttim.event.entity;

/**
 * @author xyx
 * @date 2024/9/19
 */
public class SessionRenewalEvent extends BaseEvent{
    public SessionRenewalEvent(Integer userId) {
        super(userId);
    }
}
