package life.majiang.community2.controller;

import life.majiang.community2.dto.CommentCreateDTO;
import life.majiang.community2.dto.CommentDTO;
import life.majiang.community2.dto.ResultDTO;
import life.majiang.community2.enums.CommentTypeEnum;
import life.majiang.community2.exception.CustomizeErrorCode;
import life.majiang.community2.model.Comment;
import life.majiang.community2.model.User;
import life.majiang.community2.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-11-08 00:04
 **/

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                        HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);

        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}

    