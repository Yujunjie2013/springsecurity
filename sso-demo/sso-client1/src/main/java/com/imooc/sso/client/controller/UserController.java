package com.imooc.sso.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 查询用户信息
     *
     * @param authentication 用户信息
     * @return
     */
    @RequestMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }

}