package com.epam.rd.tasks.sqlqueries;

/**
 * Implement sql queries like described
 */
public class SqlQueries {
    //Select all employees sorted by last name in ascending order
    //language=HSQLDB
    String select01 = "SELECT * FROM Employee " +
            "ORDER BY LASTNAME ASC";

    //Select employees having no more than 5 characters in last name sorted by last name in ascending order
    //language=HSQLDB
    String select02 = "SELECT * FROM Employee " +
            "WHERE LENGTH(LASTNAME) <=5" +
            "ORDER BY LASTNAME ASC";

    //Select employees having salary no less than 2000 and no more than 3000
    //language=HSQLDB
    String select03 = "SELECT * FROM Employee " +
            "WHERE SALARY <= 3000 AND SALARY >= 2000 " +
            "ORDER BY LASTNAME ASC";

    //Select employees having salary no more than 2000 or no less than 3000
    //language=HSQLDB
    String select04 = "SELECT * FROM Employee " +
            "WHERE SALARY >= 3000 OR SALARY <= 2000 " +
            "ORDER BY LASTNAME ASC";

    //Select all employees assigned to departments and corresponding department
    //language=HSQLDB
    String select05 = "Select Employee.*, Department.NAME FROM Employee " +
            "JOIN Department ON Employee.Department = Department.Id ";

    //Select all employees and corresponding department name if there is one.
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select06 = "SELECT Employee.*, Department.NAME AS depname " +
            "FROM Employee " +
            "LEFT JOIN Department ON Employee.Department = Department.Id";

    //Select total salary pf all employees. Name it "total".
    //language=HSQLDB
    String select07 = "SELECT SUM(Salary) As total FROM Employee";

    //Select all departments and amount of employees assigned per department
    //Name column containing name of the department "depname".
    //Name column containing employee amount "staff_size".
    //language=HSQLDB
    String select08 = "SELECT D.NAME AS depname, COUNT(E.ID) AS staff_size " +
            "FROM DEPARTMENT D " +
            "JOIN EMPLOYEE E ON D.ID = E.DEPARTMENT " +
            "GROUP BY D.NAME";

    //Select all departments and values of total and average salary per department
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select09 = "SELECT D.NAME As depname, SUM(E.SALARY) As total, AVG(E.SALARY) As average " +
            "FROM Department D " +
            "JOIN EMPLOYEE E ON D.ID = E.Department " +
            "GROUP BY D.NAME";

    //Select lastnames of all employees and lastnames of their managers if an employee has a manager.
    //Name column containing employee's lastname "employee".
    //Name column containing manager's lastname "manager".
    //language=HSQLDB
    String select10 = "Select E1.LASTNAME As employee, E2.LASTNAME As manager " +
            "From Employee E1 " +
            "LEFT JOIN Employee E2 ON E1.MANAGER = E2.ID";


}
