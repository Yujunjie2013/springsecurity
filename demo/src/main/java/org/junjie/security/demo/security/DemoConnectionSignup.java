package org.junjie.security.demo.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 当我们想在用户授权后入股用户不存在默认创建用户时需要实现此接口
 * 会在SocialConfig类中添加进去
 */
@Component
public class DemoConnectionSignup implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        //这里为了简单处理，就直接返回用户名
        return connection.getDisplayName();
    }
}
