package com.example.demo.form;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.model.User.Gender;
import com.example.demo.validator.Enum;
import com.example.demo.validator.UniqueUsername;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterForm {
    
    @NotBlank
    @Size(max = 20)
    @UniqueUsername
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotBlank
    @Email
    private String email;

    @Enum(enumClass = Gender.class)
    private String gender;

    public final static Map<String, String> genderOptions = Arrays.stream(Gender.values())
        .collect(
            Collectors.toMap(
                gender -> gender.getLabel(),
                gender -> gender.name(),
                (u, v) -> v,
                LinkedHashMap::new
            )
        );
}
