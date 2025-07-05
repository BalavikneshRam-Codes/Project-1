package com.busoft.project1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String home(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        return "pages/login";
    }
    @GetMapping("/login")
    public String Login(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        return "pages/login";
    }
}
