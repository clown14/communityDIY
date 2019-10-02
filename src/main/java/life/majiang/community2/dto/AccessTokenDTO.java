package life.majiang.community2.dto;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-18 22:49
 **/

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}

    