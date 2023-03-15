package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    
    @GetMapping("/login")
    public String login()
    {
        return "security/login";
    }

    @GetMapping("/mypage")
    public String showMyPage()
    {
        return "security/mypage";
    }
}
