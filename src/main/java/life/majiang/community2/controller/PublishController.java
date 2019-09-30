package life.majiang.community2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-26 22:17
 **/

@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "/publish";
    }
}

    