package org.junjie.security.browser.support;

import lombok.Data;

@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickName;
    private String headImg;
}
