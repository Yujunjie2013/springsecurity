package com.imooc.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient1Appcalition {
    //    SsoSecurityConfigurer
    public static void main(String[] args) {
        SpringApplication.run(SsoClient1Appcalition.class, args);
    }

}
