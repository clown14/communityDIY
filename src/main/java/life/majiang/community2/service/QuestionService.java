package life.majiang.community2.service;

import life.majiang.community2.dto.QuestionDTO;
import life.majiang.community2.mapper.QuestionMapper;
import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.Question;
import life.majiang.community2.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-02 15:47
 **/

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //原理 target.set + source的属性名（source.get + source的属性名）：所有source必须有get方法，target必须有set方法
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}

    