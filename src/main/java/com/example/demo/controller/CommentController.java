package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentRepository repository;

    @GetMapping
    public String getComments(
        @ModelAttribute Comment comment,
        Model model
    )
    {
        model.addAttribute("comments", repository.findAll());
        return "comment/list";
    }

    @PostMapping
    public String addComment(
        @Validated @ModelAttribute Comment comment,
        BindingResult result,
        Model model
    )
    {
        model.addAttribute("comments", repository.findAll());
        if (result.hasErrors()) {
            return "comment/list";
        }

        repository.save(comment);
        return "redirect:/comment";
    }
}
