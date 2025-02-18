package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.demo.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                    ).permitAll()
                    .antMatchers("/register", "/login").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/admin/**").hasAuthority(User.Authority.ADMIN.name())
                    .anyRequest().authenticated()
            )
            .formLogin(loginConfig -> loginConfig.loginPage("/login").defaultSuccessUrl("/mypage").permitAll())
            .logout(logoutConfig -> logoutConfig.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
            .rememberMe(rm -> rm.key("remember-me-key"));

        http.csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }
}
