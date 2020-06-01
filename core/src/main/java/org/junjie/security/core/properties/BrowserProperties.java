package org.junjie.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    /**
     * 登录页面，如果用户没有配置，则使用默认的登录页面/imooc-sign.html
     */
    private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    /**
     * 登录页面,成功后是返回Json还是重定向
     */
    private LoginType loginType = LoginType.JSON;
    /**
     * 记住我有效时长,默认1小时
     */
    private int rememberMeSeconds = 3600;
}
