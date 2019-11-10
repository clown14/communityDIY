package life.majiang.community2.controller;

import life.majiang.community2.dto.QuestionDTO;
import life.majiang.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-17 22:07
 **/

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(Model model,
                           @PathVariable(name = "id") Long id) {
        QuestionDTO questionDTO = questionService.getById(id);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}

    