package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    
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

    @GetMapping("/register")
    public String showRegisterForm(@ModelAttribute("form") RegisterForm form)
    {
        return "security/register";
    }

    @PostMapping("/register")
    public String register(
        @Validated @ModelAttribute("form") RegisterForm form,
        BindingResult result
    )
    {
        if (result.hasErrors()) {
            return "security/register";
        }

        User user = new User()
            .setUsername(form.getUsername())
            .setEmail(form.getEmail())
            .setPassword(passwordEncoder.encode(form.getPassword()))
            .setGender(form.getGender() != null ? User.Gender.valueOf(form.getGender()) : null);
        userRepository.save(user);

        return "redirect:/login?register";
    }
}
