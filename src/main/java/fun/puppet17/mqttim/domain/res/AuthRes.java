package fun.puppet17.mqttim.domain.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.puppet17.mqttim.domain.po.AclEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author xyx
 * @date 2024/9/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 忽略空字段
public class AuthRes {
    
    @JsonProperty("result")
    private String result; // "allow" | "deny" | "ignore"
    
    @JsonProperty("is_superuser")
    private Boolean isSuperuser; // true | false
    
    @JsonProperty("client_attrs")
    private Map<String, String> clientAttrs; // 可选字段
    
    @JsonProperty("expire_at")
    private Long expireAt; // 可选字段
    
    @JsonProperty("acl")
    private List<AclEntry> acl; // 可选字段
    
}

