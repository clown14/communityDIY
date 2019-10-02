package life.majiang.community2.dto;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-18 22:50
 **/

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}

    