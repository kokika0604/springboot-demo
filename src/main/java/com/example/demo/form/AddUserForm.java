package com.example.demo.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Value;

@Value
public class AddUserForm {

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^[0-9-]*$", message = "{PhoneNumber}")
    private String phoneNumber;

    @DateTimeFormat(iso = ISO.DATE)
    @Past
    private LocalDate birthday;
}
