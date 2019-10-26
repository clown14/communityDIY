package life.majiang.community2.service;

import life.majiang.community2.dto.PaginationDTO;
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

//    public List<QuestionDTO> list() {
//        List<Question> questions = questionMapper.list();
//        List<QuestionDTO> questionDTOList = new ArrayList<>();
//        for (Question question : questions) {
//            User user = userMapper.findById(question.getCreator());
//            QuestionDTO questionDTO = new QuestionDTO();
//            //原理 target.set + source的属性名（source.get + source的属性名）：所有source必须有get方法，target必须有set方法
//            BeanUtils.copyProperties(question, questionDTO);
//            questionDTO.setUser(user);
//            questionDTOList.add(questionDTO);
//        }
//        return questionDTOList;
//    }

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        if (page < 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //原理 target.set + source的属性名（source.get + source的属性名）：所有source必须有get方法，target必须有set方法
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(id);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(id, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

}

    