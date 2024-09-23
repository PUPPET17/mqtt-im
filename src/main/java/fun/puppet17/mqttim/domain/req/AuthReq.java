package fun.puppet17.mqttim.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xyx
 * @date 2024/9/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthReq {
    
    private String clientid;
    
    private String username;
    
    private String password;
}
