package fun.puppet17.mqttim.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xyx
 * @date 2024/9/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    
    private String clientid;
    
    private String username;
    
    private String password;
    
    private List<AclEntry> acl;
}
