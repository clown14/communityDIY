package life.majiang.community2.dto;

import life.majiang.community2.model.User;
import lombok.Data;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-02 15:47
 **/
/*
* DTO就是数据传输对象(Data Transfer Object)的缩写。DTO模式，是指将数据封装成普通的JavaBeans，在J2EE多个层次之间传输。
* DTO类似信使，是同步系统中的Message。
* 这样带来的好处有：

1.依据现有的类代码，即可方便的构造出DTO对象，而无需重新进行分析。

2.减少请求次数，大大提高效率。

3.按需组织DTO对象，页面需要的字段我才组织，不需要的我不组织，可以避免传输整个表的字段，一定程度上提高了安全性。

结合个人的开发经验来谈一下用法：
一般我们使用DTO类来继承entity实体类，在DTO类里放一些业务字段，并提供get、set方法。当我们在业务逻辑层或者交互层用到一些数据库中不存在的字段时，
我们就需要在DTO类里放这些字段，这些字段的意义就相当于一些经处理过的数据库字段，实质意义就是方便数据交互，提高效率。
*/
@Data
public class QuestionDTO {
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
    private User user;
}

    