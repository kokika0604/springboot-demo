package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // 抽象メソッドを定義するだけでクエリが自動作成される
    Collection<Employee> findByNameLike(String name);

    // JPQL
    @Query("SELECT e FROM Employee e WHERE e.name LIKE :name")
    Collection<Employee> searchByName(String name);

    // raw query
    @Query(value = "SELECT * FROM employee WHERE name LIKE :name", nativeQuery = true)
    Collection<Employee> searchByNameRaw(String name);
}
