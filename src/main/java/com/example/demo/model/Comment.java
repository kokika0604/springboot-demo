package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Comment {
    
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Size(max = 10)
    private String content;
}
