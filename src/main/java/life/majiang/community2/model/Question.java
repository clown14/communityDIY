package life.majiang.community2.model;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-01 15:26
 **/

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}

    