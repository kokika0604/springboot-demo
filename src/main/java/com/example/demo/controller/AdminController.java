package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final UserRepository userRepository;

    @GetMapping("/userList")
    public String showUserList(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user_list";
    }
}
