package fun.puppet17.mqttim.event.entity;

/**
 * @author xyx
 * @date 2024/9/19
 */
public class OfflineEvent extends BaseEvent{
    public OfflineEvent(Integer userId) {
        super(userId);
    }
}
