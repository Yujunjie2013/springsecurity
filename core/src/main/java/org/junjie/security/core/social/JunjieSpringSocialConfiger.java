package org.junjie.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class JunjieSpringSocialConfiger extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    private String signUpUrl;

    public JunjieSpringSocialConfiger(String signUpUrl, String filterProcessesUrl) {
        this.signUpUrl = signUpUrl;
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        //自定义过滤的url地址
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl(signUpUrl);
        return (T) filter;
    }
}
