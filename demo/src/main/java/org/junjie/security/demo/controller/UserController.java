package org.junjie.security.demo.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junjie.security.app.social.impl.AppSignUpUtils;
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
    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request) {
        return user;
    }

// web
//    @PostMapping("/regist")
//    public void regist(User user, HttpServletRequest request, HttpServletResponse response) {
//        logger.info("开始注册用户逻辑");
//        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
////        String userId = user.getUsername();
//        String userId = user.getId();
//        if (StringUtils.isEmpty(userId)) {
//            userId = RandomStringUtils.random(8, false, true);
//        }
//        logger.info("注册用户userId:" + userId);
//        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request, response));
//    }

    //app
    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("开始注册用户逻辑");
        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
//        String userId = user.getUsername();
        String userId = user.getId();
        if (StringUtils.isEmpty(userId)) {
            userId = RandomStringUtils.random(8, false, true);
        }
        logger.info("注册用户userId:" + userId);
//        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request, response));
        appSignUpUtils.doPostSignUp(new ServletWebRequest(request, response), userId);
    }
}
