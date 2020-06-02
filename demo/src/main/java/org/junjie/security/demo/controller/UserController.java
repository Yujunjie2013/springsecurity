package org.junjie.security.demo.controller;

import org.junjie.security.demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request) {
        return user;
    }

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("开始注册用户逻辑");
        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
//        String userId = user.getUsername();
        String userId = user.getId();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request, response));
    }
}
