package life.majiang.community2.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-11-26 23:19
 **/

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
    