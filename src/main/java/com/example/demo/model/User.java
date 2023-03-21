package com.example.demo.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private Gender gender = null;
    
    @Column(nullable = false)
    private Authority authority = Authority.USER;

    public Boolean isAdmin()
    {
        return this.authority == Authority.ADMIN;
    }

    public enum Gender {
        MALE("男性"),
        FEMALE("女性"),
        OTHER("その他");

        @Getter
        private String label;

        Gender(String label) {
            this.label = label;
        }

        // public final static Map<String, Gender> LIST_MAPPED_BY_NAME = new LinkedHashMap<>();

        // public final static List<String> NAMES = new ArrayList<>();

        // static {
        //     for (Gender gender: Gender.values()) {
        //         LIST_MAPPED_BY_NAME.put(gender.toString(), gender);
        //         NAMES.add(gender.toString());
        //     }
        // }
    }

    public enum Authority {
        ADMIN("管理者"),
        USER("ユーザ");

        @Getter
        private String label;

        Authority(String label) {
            this.label = label;
        }
    }
}
