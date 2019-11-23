package life.majiang.community2.mapper;

import life.majiang.community2.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}