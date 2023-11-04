package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<Employee> createEmployee(Employee employee);

    ResponseEntity<Page<Employee>> retrieveAllEmployees(Pageable pageable);

    ResponseEntity<Employee> retrieveEmployee(Long employeeID);

    void deleteEmployee(Long employeeID);

    ResponseEntity<Page<Employee>> retrieveUserEmployees(Pageable pageable, Long userID);
}
