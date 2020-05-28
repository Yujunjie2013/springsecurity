package org.junjie.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "org.junjie")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
