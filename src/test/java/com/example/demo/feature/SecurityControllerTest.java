package com.example.demo.feature;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.form.RegisterForm;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SecurityControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("新規登録失敗")
    void registerationFailed() throws Exception
    {
        mockMvc
            .perform(
                post("/register")
                    .flashAttr("form", new RegisterForm())
                    .with(csrf())
            )
            .andExpect(model().attributeHasFieldErrors("form", "username", "email", "password"))
            .andExpect(view().name("security/register"));
    }

    @Test
    @DisplayName("新規登録成功")
    void registerationSucceeded() throws Exception
    {
        RegisterForm form = new RegisterForm()
            .setUsername("user99")
            .setEmail("k.kou@customedia.co.jp")
            .setPassword("12345678qO");

        mockMvc
            .perform(
                post("/register")
                    .flashAttr("form", form)
                    .with(csrf())
            )
            .andExpect(model().hasNoErrors())
            .andExpect(redirectedUrl("/login?register"));
    }

    @Test
    @DisplayName("管理者はユーザ一覧を見れる")
    @WithMockUser(username="admin", authorities="ADMIN")
    void loggedInAsAdminUser() throws Exception {

        mockMvc.perform(get("/admin/userList"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("ユーザ一覧")))
            .andExpect(view().name("admin/user_list"));
    }
}
