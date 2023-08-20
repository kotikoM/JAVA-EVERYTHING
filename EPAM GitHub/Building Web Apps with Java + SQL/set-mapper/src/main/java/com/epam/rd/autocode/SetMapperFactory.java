package com.epam.rd.autocode;


import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SetMapperFactory {

    public SetMapper<Set<Employee>> employeesSetMapper() {
        return new SetMapper<Set<Employee>>() {

            final List<Integer> ids = new ArrayList<>(); //list of all the ids that are in the resultSet

            @Override
            public Set<Employee> mapSet(ResultSet resultSet) {
                Set<Employee> employeeSet = new HashSet<>();

                try {
                    int numOfEmployees = 0;

                    while (resultSet.next()) {
                        numOfEmployees++;
                        ids.add(resultSet.getInt("id"));
                    }

                    resultSet.beforeFirst();
                    resultSet.next();

                    while (employeeSet.size() != numOfEmployees) {
                        int managerID = resultSet.getInt("manager");

                        //logic for skipping current row
                        if (hasManager(managerID, employeeSet)) {
                            //add employee that has a manager to the list
                            employeeSet.add(new Employee(
                                    BigInteger.valueOf(resultSet.getInt("id")),
                                    getFullName(resultSet),
                                    getPosition(resultSet.getString("position")),
                                    resultSet.getDate("hireDate").toLocalDate(),
                                    resultSet.getBigDecimal("salary"),
                                    getManager(managerID, employeeSet)
                            ));
                        }

                        //logic for looping back the resultSet
                        if (!resultSet.next()) {
                            resultSet.beforeFirst();
                            resultSet.next();
                        }
                    }

                    return employeeSet;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            public boolean hasManager(int managerId, Set<Employee> employeeSet) {
                if (managerId == 0 || !ids.contains(managerId)) {
                    return true;
                }

                for (Employee employee : employeeSet) {
                    if (Objects.equals(employee.getId(), BigInteger.valueOf(managerId))) {
                        return true;
                    }
                }

                return false;
            }

            public Employee getManager(int managerID, Set<Employee> employeeSet) {
                if (managerID == 0 || !ids.contains(managerID)) {
                    return null;
                }

                for (Employee employee : employeeSet) {
                    if (Objects.equals(employee.getId(), BigInteger.valueOf(managerID))) {
                        return employee;
                    }
                }

                return null;
            }

            public FullName getFullName(ResultSet resultSet) throws SQLException {
                return new FullName(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("middleName"));
            }

            public Position getPosition(String position) throws SQLException {
                switch (position.toLowerCase()) {
                    case "president":
                        return Position.PRESIDENT;
                    case "manager":
                        return Position.MANAGER;
                    case "analyst":
                        return Position.ANALYST;
                    case "clerk":
                        return Position.CLERK;
                    default:
                        return Position.SALESMAN;
                }
            }
        };
    }
}
