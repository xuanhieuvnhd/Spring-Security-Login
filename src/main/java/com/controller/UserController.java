package com.controller;

import java.security.Principal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        // Nhận tên người dùng được xác thực
        System.out.println(principal.getName());
        return new ModelAndView("/user");
    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        // Nhận tên người dùng được xác thực từ SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        return new ModelAndView("/admin");
    }
}
