package life.majiang.community2.controller;

import life.majiang.community2.dto.PaginationDTO;
import life.majiang.community2.dto.QuestionDTO;
import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.User;
import life.majiang.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-11 21:31
 **/

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length != 0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if (user != null) {
//                        request.getSession().setAttribute("user", user);
//                    }
//                    break;
//                }
//            }
//        }

//        List<QuestionDTO> questions = questionService.list();
//        model.addAttribute("questions", questions);

        PaginationDTO pagination  = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}

    