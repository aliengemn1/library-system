package com.example.library.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
    
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }
    
    @GetMapping("/catalog")
    public String catalogManagement() {
        return "admin/catalog";
    }
    
    @GetMapping("/patrons")
    public String patronManagement() {
        return "admin/patrons";
    }
    
    @GetMapping("/circulation")
    public String circulationManagement() {
        return "admin/circulation";
    }
    
    @GetMapping("/reports")
    public String reports() {
        return "admin/reports";
    }
    
    @GetMapping("/settings")
    public String settings() {
        return "admin/settings";
    }
} 