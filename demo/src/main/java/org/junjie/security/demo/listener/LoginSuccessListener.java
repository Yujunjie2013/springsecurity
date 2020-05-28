package org.junjie.security.demo.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LoginSuccessListener {
    @EventListener
    public void listenEvent(InteractiveAuthenticationSuccessEvent event) {
        System.out.println("监听到登录成功事件; 接收到的值：" + event.getAuthentication() + "；发布的时间为" + Instant.ofEpochMilli(event.getTimestamp()));
    }
}
