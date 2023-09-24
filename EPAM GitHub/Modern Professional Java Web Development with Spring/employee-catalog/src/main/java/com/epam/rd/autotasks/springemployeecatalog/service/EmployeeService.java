package com.epam.rd.autotasks.springemployeecatalog.service;


import com.epam.rd.autotasks.springemployeecatalog.repository.EmployeeRepository;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.getAllEmployees(pageable);
    }

    public Employee getEmployeeByID(long employeeID, boolean needFullChain) {
        return employeeRepository.getEmployeeByID(employeeID, needFullChain);
    }

    public List<Employee> getEmployeesByMngrID(long mngrID, Pageable pageable) {
        return employeeRepository.getEmployeesByMngrID(mngrID, pageable);
    }

    public List<Employee> getEmployeesByDep(String depIDorName, Pageable pageable) {
        String numRegex = "\\p{N}*";

        if (depIDorName.matches(numRegex)) {
            return employeeRepository.getEmployeesByDepID(Long.parseLong(depIDorName), pageable);
        }
        return employeeRepository.getEmployeesByDepName(depIDorName, pageable);
    }
}
