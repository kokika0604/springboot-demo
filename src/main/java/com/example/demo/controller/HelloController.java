package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.AddUserForm;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping
    public String index() {
        return "hello/hello";
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(required = false, defaultValue = "hello") String message,
            Model model) {
        model.addAttribute("message", message);
        return "hello/hello";
    }

    @GetMapping("/form")
    public String showForm(
            @ModelAttribute(name = "form") AddUserForm form) {
        return "hello/form";
    }

    @PostMapping("/form")
    public String confirm(
            @RequestParam Map<String, String> params,
            @Validated @ModelAttribute(name = "form") AddUserForm form,
            BindingResult result) {
        if (params.containsKey("back")) {
            return "hello/form";
        }
        if (result.hasErrors()) {
            return "hello/form";
        }
        if (params.containsKey("save")) {
            return "hello/result";
        }
        return "hello/confirm";
    }

    @InitBinder
    public void initbinder(WebDataBinder binder) {
        // テキストフィールドの入力値がない場合はnullに変換する
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}

// TODO: 1.thymeleafの使い方 2.カスタムアノテーション
