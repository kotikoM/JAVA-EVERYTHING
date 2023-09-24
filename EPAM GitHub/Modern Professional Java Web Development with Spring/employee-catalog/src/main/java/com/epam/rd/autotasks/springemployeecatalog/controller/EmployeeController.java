package com.epam.rd.autotasks.springemployeecatalog.controller;


import com.epam.rd.autotasks.springemployeecatalog.service.EmployeeService;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("employees")
@SuppressWarnings("unused")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeByID(
            @PathVariable("employeeId") long employeeID,
            @RequestParam(value = "full_chain", defaultValue = "false") boolean needFullChain){
        return employeeService.getEmployeeByID(employeeID, needFullChain);
    }

    @GetMapping("/by_manager/{managerId}")
    public List<Employee> getEmployeesByMngrID(
            @PathVariable("managerId") long mngrID, Pageable pageable){
        return employeeService.getEmployeesByMngrID(mngrID, pageable);
    }

    @GetMapping("/by_department/{department}")
    public List<Employee> getEmployeesByDep(
            @PathVariable("department") String depIDorName, Pageable pageable){
        return employeeService.getEmployeesByDep(depIDorName, pageable);
    }
}
