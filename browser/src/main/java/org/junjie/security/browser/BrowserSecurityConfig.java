package org.junjie.security.browser;

import org.junjie.security.core.properties.SecurityProperties;
import org.junjie.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(browserAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//将自定义的图形验证码加到用户密码过滤器之前
                .formLogin()
    //                .loginPage("/imooc-sign.html")//指定了跳转到登录页面的请求URL,这里的静态界面一定要放到static目录下，否则找不到
                    .loginPage("/authentication/require")//指定了跳转到登录页面的请求URL,这里的接口在BrowsersecurityController里
                    .loginProcessingUrl("/authentication/form")//自定义的登录url，UsernamePasswordAuthenticationFilter来处理
                    .successHandler(browserAuthenticationSuccessHandler)//自定义成功处理器
                    .failureHandler(browserAuthenticationFailureHandler)//自定义失败处理器
                    .and()
                 .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image")
                .permitAll()//表示跳转到登录页面的请求不被拦截
                .anyRequest()// 所有请求
                .authenticated()
                .and()
                .csrf()//CSRF攻击防御关了
                .disable();//都需要认证
    }
}
