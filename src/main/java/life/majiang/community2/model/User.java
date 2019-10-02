package life.majiang.community2.model;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-19 19:10
 **/

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}

    