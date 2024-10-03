package com.hamza.springboot.training.thymeleaf.service;

import com.hamza.springboot.training.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
