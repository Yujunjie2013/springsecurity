package org.junjie.security.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 当我们想在用户授权后入股用户不存在默认创建用户时需要实现此接口
 * 会在SocialConfig类中添加进去
 */
@Component
@Slf4j
public class DemoConnectionSignup implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        //这里为了简单处理，就直接返回用户名
        String displayName = connection.getDisplayName();
        String userId = RandomStringUtils.random(8, false, true);
//        log.info("用户名称:" + displayName + "---userId:" + userId);
        return userId;
    }
}
