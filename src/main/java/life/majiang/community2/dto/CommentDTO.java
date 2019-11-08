package life.majiang.community2.dto;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-11-08 13:44
 **/

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}

    