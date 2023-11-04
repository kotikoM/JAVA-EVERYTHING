package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EMPLOYEE_EMPLOYEEID)
    private Long employeeID;

    @Column(name = EMPLOYEE_FIRSTNAME)
    private String firstName;

    @Column(name = EMPLOYEE_LASTNAME)
    private String lastName;

    @Column(name = EMPLOYEE_CONTACTINFO)
    private String contactInfo;

    @Column(name = EMPLOYEE_ROLE)
    private String role;

    @Column(name = EMPLOYEE_SALARY)
    private Double salary;

    @Column(name = EMPLOYEE_USERID)
    private Long userID;

    public Employee(Long employeeID, String firstName, String lastName, String contactInfo, String role, Double salary, Long userID) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.role = role;
        this.salary = salary;
        this.userID = userID;
    }

    public Employee() {
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getRole() {
        return role;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getUserID() {
        return userID;
    }
}
