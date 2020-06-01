package org.junjie.security.core.social.qq.config;

import org.junjie.security.core.properties.QQProperties;
import org.junjie.security.core.properties.SecurityProperties;
import org.junjie.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
//这里表示只有配置了org.junjie.social.qq中的app-id配置项，下面的配置才生效
@ConditionalOnProperty(prefix = "org.junjie.social.qq", name = "appId")
public class QQAutoConfig extends /*SocialAutoConfigurerAdapter*/SocialConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    public QQAutoConfig() {
    }

    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        QQConnectionFactory qqConnectionFactory = new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
        return qqConnectionFactory;
    }
}
