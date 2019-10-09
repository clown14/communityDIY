package life.majiang.community2.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import life.majiang.community2.mapper.QuestionMapper;
import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.Question;
import life.majiang.community2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-26 22:17
 **/

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublic(@RequestParam(value = "title",required = false) String title,
                           @RequestParam(value = "description",required = false) String description,
                           @RequestParam(value = "tag",required = false) String tag,
                           HttpServletRequest request,
                           Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);


        Cookie[] cookies = request.getCookies();
        User user=null;
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());

        questionMapper.insert(question);
        return "redirect:/";
    }
}

    