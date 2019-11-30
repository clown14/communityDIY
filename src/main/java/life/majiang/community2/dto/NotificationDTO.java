package life.majiang.community2.dto;

import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-11-30 16:04
 **/

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}

    