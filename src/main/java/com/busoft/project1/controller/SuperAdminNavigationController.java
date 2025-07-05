package com.busoft.project1.controller;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperAdminNavigationController {
    @GetMapping("/superAdminLogin")
    public String Login(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        return "pages/superAdminLogin";
    }
    @GetMapping("/superAdminDashboard")
    public String superAdminDashboard(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        return "pages/superAdminDashboard";
    }
    @GetMapping("/createCompany")
    public String createCompany(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        return "pages/CreateCompany";
    }
}
