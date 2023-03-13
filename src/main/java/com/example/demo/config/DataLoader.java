package com.example.demo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final DepartmentRepository departmentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        departmentRepository.saveAll({
        });
    }
    
}
