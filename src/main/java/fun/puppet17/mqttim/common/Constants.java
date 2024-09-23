package fun.puppet17.mqttim.common;

/**
 * @author xyx
 * @date 2024/9/20
 */
public class Constants {
    
    public enum QoS {
        AT_MOST_ONCE(0),
        AT_LEAST_ONCE(1),
        EXACTLY_ONCE(2);
        
        private final int level;
        
        QoS(int level) {
            this.level = level;
        }
        
        public int getLevel() {
            return level;
        }
        
        public static QoS fromLevel(int level) {
            for (QoS qos : values()) {
                if (qos.getLevel() == level) {
                    return qos;
                }
            }
            throw new IllegalArgumentException("Invalid QoS level: " + level);
        }
    }
    
    public enum Permission {
        ALLOW("allow"),
        DENY("deny");
        
        private final String value;
        
        Permission(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static Permission fromValue(String value) {
            for (Permission permission : values()) {
                if (permission.getValue().equalsIgnoreCase(value)) {
                    return permission;
                }
            }
            throw new IllegalArgumentException("Invalid permission value: " + value);
        }
    }
    
    public enum Action {
        SUBSCRIBE("subscribe"),
        PUBLISH("publish"),
        ALL("all");
        
        private final String value;
        
        Action(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static Action fromValue(String value) {
            for (Action action : values()) {
                if (action.getValue().equalsIgnoreCase(value)) {
                    return action;
                }
            }
            throw new IllegalArgumentException("Invalid action value: " + value);
        }
    }
}
