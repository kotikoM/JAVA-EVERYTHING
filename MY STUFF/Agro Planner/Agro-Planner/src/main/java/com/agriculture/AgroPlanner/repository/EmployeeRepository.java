package com.agriculture.AgroPlanner.repository;

import com.agriculture.AgroPlanner.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByUserID(Pageable pageable, Long userID);
}
