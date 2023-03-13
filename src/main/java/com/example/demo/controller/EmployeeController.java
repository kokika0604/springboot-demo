package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeRepository repository;

    public String showEmployees(Model model)
    {
        model.addAttribute("employees", repository.findAll());
        return "employee/list";
    }
}
