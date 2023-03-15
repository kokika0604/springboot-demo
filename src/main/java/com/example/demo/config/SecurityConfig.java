package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

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
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(loginConfig -> loginConfig.loginPage("/login").defaultSuccessUrl("/mypage").permitAll())
            .logout(logoutConfig -> logoutConfig.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
            .rememberMe(withDefaults());

        http.csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " not found")
            );
            return new User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ADMIN")
            );
        };
    }
}
