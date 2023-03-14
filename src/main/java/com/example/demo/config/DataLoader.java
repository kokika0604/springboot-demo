package com.example.demo.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;

import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Department> departments = List.of(
            new Department().setName("営業"),
            new Department().setName("開発")
        );
        departmentRepository.saveAll(departments);
        employeeRepository.save(new Employee().setName("user1").setDepartment(departments.get(0)));
    }

}
