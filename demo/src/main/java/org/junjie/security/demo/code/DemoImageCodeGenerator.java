package org.junjie.security.demo.code;

import org.junjie.security.core.validate.code.ImageCode;
import org.junjie.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义的图形验证码
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest servletWebRequest) {
        System.out.println("自定义的图形验证码生成代码");
        return null;
    }
}
