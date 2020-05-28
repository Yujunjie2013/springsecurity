package org.junjie.security.core;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//使配置的ConfigurationProperties生效
@ConfigurationPropertiesScan(basePackages = {"org.junjie"})
public class SecurityCoreConfig {
}
