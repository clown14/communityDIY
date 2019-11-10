package life.majiang.community2.mapper;

import life.majiang.community2.model.Question; /**
 * @program: community2
 * @author: onion
 * @create: 2019-11-06 23:49
 **/

public interface QuestionExtMapper {
    void incView(Question question);
    void incCommentCount(Question question);
}

    