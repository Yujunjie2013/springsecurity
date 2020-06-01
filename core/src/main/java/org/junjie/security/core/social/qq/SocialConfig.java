package org.junjie.security.core.social.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@EnableSocial
@Configuration
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//        jdbcUsersConnectionRepository.setTablePrefix("");//如果表名有前缀可以加上
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        return new SpringSocialConfigurer();
    }
}
