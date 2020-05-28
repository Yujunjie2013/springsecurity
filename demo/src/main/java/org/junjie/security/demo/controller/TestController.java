package org.junjie.security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/get")
    public Authentication getme() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
