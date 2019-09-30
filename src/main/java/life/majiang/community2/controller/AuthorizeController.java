package life.majiang.community2.controller;

import life.majiang.community2.dto.AccessTokenDTO;
import life.majiang.community2.dto.GithubUser;
import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.User;
import life.majiang.community2.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-18 22:28
 **/

@Controller
public class AuthorizeController {
    @Autowired
    //Component已经注入容器，直接加载
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           //HttpServletRequest request,
                           HttpServletResponse response) {    //在request中拿到session，spring自动加载

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();   //ctrl+alt+v 快速实例化
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);


        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            //UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)全局唯一标识符,
            //是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的 形式。
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

//          登陆成功，写cookie和session
//          request.getSession().setAttribute("user", githubUser);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";    //跳转，重定向，地址改变
        } else {
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }
}

    