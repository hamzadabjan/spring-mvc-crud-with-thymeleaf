package com.hamza.springboot.training.thymeleaf.dao;

import com.hamza.springboot.training.thymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



    // Add method to sort by first name
    public List<Employee> findAllByOrderByFirstNameAsc();

    // Find employees whose emails are from a specific domain
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:emailDomain%")
    List<Employee> findEmployeesByEmailDomain(@Param("emailDomain") String emailDomain);

}
