package life.majiang.community2.dto;

import life.majiang.community2.model.User;
import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-11-15 20:40
 **/

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}

    