package fun.puppet17.mqttim.domain.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import fun.puppet17.mqttim.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xyx
 * @date 2024/9/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AclEntry {
    
    @JsonProperty("permission")
    private Constants.Permission permission;//ALLOW("allow"),DENY("deny");
    
    @JsonProperty("action")
    private Constants.Action action;//SUBSCRIBE("subscribe"),PUBLISH("publish"),ALL("all");
    
    @JsonProperty("topic")
    private String topic;
    
    @JsonProperty("qos")
    private List<Constants.QoS> qos;//AT_MOST_ONCE(0),AT_LEAST_ONCE(1),EXACTLY_ONCE(2);
}