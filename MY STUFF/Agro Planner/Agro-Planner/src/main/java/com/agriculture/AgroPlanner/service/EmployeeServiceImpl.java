package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Employee;
import com.agriculture.AgroPlanner.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    private static ResponseEntity<Page<Employee>> getPageResponseEntity(Page<Employee> allEmployees) {
        return (allEmployees.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allEmployees);
    }

    public ResponseEntity<Employee> createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    public ResponseEntity<Page<Employee>> retrieveAllEmployees(Pageable pageable) {
        Page<Employee> allEmployees = employeeRepository.findAll(pageable);
        return getPageResponseEntity(allEmployees);
    }

    public ResponseEntity<Employee> retrieveEmployee(Long employeeID) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeID);
        return employeeOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteEmployee(Long employeeID) {
        employeeRepository.deleteById(employeeID);
    }

    public ResponseEntity<Page<Employee>> retrieveUserEmployees(Pageable pageable, Long userID) {
        Page<Employee> employeesPage = employeeRepository.findByUserID(pageable, userID);
        return getPageResponseEntity(employeesPage);
    }
}
