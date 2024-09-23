package fun.puppet17.mqttim.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权类
 *
 * @author xyx
 * @date 2024/9/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationReq {
    
    private String clientid;
    private String topic;
}
