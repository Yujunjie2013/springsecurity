package org.junjie.security.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties {
    private int width = 100; // 验证码图片宽度
    private int height = 36; // 验证码图片长度
    private int length = 4; // 验证码位数
    private int expireIn = 60; // 验证码有效时间 60s
    private String url;//拦截的地址
}
