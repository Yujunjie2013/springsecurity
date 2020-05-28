package org.junjie.security.browser;

import org.junjie.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .loginPage("/imooc-sign.html")//指定了跳转到登录页面的请求URL,这里的静态界面一定要放到static目录下，否则找不到
                .loginPage("/authentication/require")//指定了跳转到登录页面的请求URL,这里的接口在BrowsersecurityController里
                .loginProcessingUrl("/authentication/form")//自定义的登录url，UsernamePasswordAuthenticationFilter来处理
                .successHandler(browserAuthenticationSuccessHandler)//自定义成功处理器
                .failureHandler(browserAuthenticationFailureHandler)//自定义失败处理器
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll()//表示跳转到登录页面的请求不被拦截
                .anyRequest()// 所有请求
                .authenticated()
                .and()
                .csrf()//CSRF攻击防御关了
                .disable();//都需要认证
    }
}
