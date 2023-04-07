package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.User;
import com.example.demo.model.User.Authority;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserDetailServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Test
    @DisplayName("loadUserByUsernameメソッドテスト - ユーザ名が存在する場合")
    void test_loadUserByUsername_with_valid_username()
    {
        User user = new User()
            .setUsername("user")
            .setPassword("12345")
            .setAuthority(Authority.ADMIN);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        UserDetails expectedUserDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            AuthorityUtils.createAuthorityList(user.getAuthority().name())
        );
        assertEquals(expectedUserDetails, userDetailServiceImpl.loadUserByUsername(user.getUsername()));
    }

    @Test
    @DisplayName("loadUserByUsernameメソッドテスト- ユーザ名が存在しない場合")
    void test_loadUserByUsername_with_invalid_username()
    {
        String username = "not exists";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailServiceImpl.loadUserByUsername(username));
    }
}
