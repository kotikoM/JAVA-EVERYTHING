package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.Employee;
import com.agriculture.AgroPlanner.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.Endpoints.*;

@RestController
@RequestMapping(EMPLOYEES_ENDPOINT)
@SuppressWarnings("unused")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<Employee> insertEmployee(
            @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> retrieveAllEmployees(Pageable pageable) {
        return employeeService.retrieveAllEmployees(pageable);
    }

    @GetMapping(EMPLOYEE_ENDPOINT)
    public ResponseEntity<Employee> retrieveEmployee(
            @PathVariable Long employeeID) {
        return employeeService.retrieveEmployee(employeeID);
    }

    @DeleteMapping(EMPLOYEE_ENDPOINT)
    public void deleteEmployee(
            @PathVariable Long employeeID) {
        employeeService.deleteEmployee(employeeID);
    }

    @GetMapping(USER_EMPLOYEES_ENDPOINT)
    public ResponseEntity<Page<Employee>> retrieveUserEmployees(Pageable pageable,
                                                                @PathVariable Long userID) {
        return employeeService.retrieveUserEmployees(pageable, userID);
    }
}
