package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeRepository repository;

    @GetMapping
    public String showEmployees(Model model)
    {
        model.addAttribute("employees", repository.findAll());
        return "employee/list";
    }

    @GetMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee)
    {
        return "employee/form";
    }

    @PostMapping("/process")
    public String save(
        @Validated @ModelAttribute Employee employee,
        BindingResult result
    )
    {
        if (result.hasErrors()) {
            return "employee/form";
        }

        repository.save(employee);

        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model)
    {
        model.addAttribute("employee", repository.findById(id));
        return "employee/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id)
    {
        repository.deleteById(id);
        return "redirect:/employee";
    }
}
