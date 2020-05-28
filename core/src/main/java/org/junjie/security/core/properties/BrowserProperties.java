package org.junjie.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    /**
     * 登录页面，如果用户没有配置，则使用默认的登录页面/imooc-sign.html
     */
    private String loginPage = "/imooc-sign.html";
    /**
     * 登录页面,成功后是返回Json还是重定向
     */
    private LoginType loginType = LoginType.JSON;
}
