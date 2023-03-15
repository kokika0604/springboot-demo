package com.example.demo.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        List<Department> departments = List.of(
            new Department().setName("営業"),
            new Department().setName("開発")
        );
        departmentRepository.saveAll(departments);
        employeeRepository.save(new Employee().setName("user1").setDepartment(departments.get(0)));


        User user = new User()
            .setUsername("admin")
            .setPassword(passwordEncoder.encode("password"))
            .setEmail("k.kou@customedia.co.jp")
            .setAuthority(User.Authority.ADMIN);
        userRepository.save(user);
    }

}
