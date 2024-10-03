package com.hamza.springboot.training.thymeleaf.controller;


import com.hamza.springboot.training.thymeleaf.entity.Employee;
import com.hamza.springboot.training.thymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("employees")
public class EmployeeController {

   private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService=theEmployeeService;
    }

    // add mapping for (/list)

    @GetMapping("/list")
    public String listEmployees (Model theModel){

        List<Employee> theEmployees= employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

    // add mapping for (/addNewEmployee)
    @GetMapping ("/addNewEmployee")
    public String addNewEmployeeForm(Model theModel){

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);

        return "EmployeeForm";

    }

    // add mapping for (/saveNewEmployeeProcess)
    @PostMapping("/saveEmployeeProcess")
    public String saveNewEmployeeProcess(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    // add mapping for (/updateEmployeeForm)
    @GetMapping ("/updateEmployeeForm")
    public String updateEmployeeForm (@RequestParam("employeeId") int employeeId, Model model){

        Employee tempEmployee = employeeService.findById(employeeId);

        model.addAttribute("employee",tempEmployee);

        return "EmployeeForm";
    }

    // add mapping for (/deleteEmployee)
    @GetMapping ("/deleteEmployee")
    public String deleteEmployee (@RequestParam("employeeId") int theId){

        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}