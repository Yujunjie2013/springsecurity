package org.junjie.security.core.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {
    /**
     * 图形验证码
     */
    private ImageCodeProperties image = new ImageCodeProperties();
}
