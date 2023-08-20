package com.epam.rd.autocode.service;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.sql.*;
import java.util.*;

public class ServiceFactory {
    private Connection getConnection() throws SQLException {
        return ConnectionSource.instance().createConnection();
    }

    public EmployeeService employeeService() {
        return new EmployeeService() {
            private static final String GET_DP_BY_ID = "SELECT * FROM department WHERE id = ?";
            private static final String GET_EMPL_BY_ID = "SELECT * FROM employee WHERE id = ?";
            private static final String SORT_BY_HIREDATE = "SELECT * FROM employee ORDER BY hiredate LIMIT ?, ?";
            private static final String SORT_BY_LASTNAME = "SELECT * FROM employee ORDER BY lastname LIMIT ?, ?";
            private static final String SORT_BY_SALARY = "SELECT * FROM employee ORDER BY salary LIMIT ?, ?";
            private static final String SORT_BY_DP_AND_LASTNAME = "SELECT * FROM employee " +
                    "ORDER BY department, lastname LIMIT ?, ?";
            private static final String SELECT_BY_DP_SORT_BY_HIREDATE = "SELECT * FROM employee " +
                    "WHERE department = ? ORDER BY hiredate LIMIT ?, ?";
            private static final String SELECT_BY_DP_SORT_BY_SALARY = "SELECT * FROM employee " +
                    "WHERE department = ? ORDER BY salary LIMIT ?, ?";
            private static final String SELECT_BY_DP_SORT_BY_LASTNAME = "SELECT * FROM employee " +
                    "WHERE department = ? ORDER BY lastname LIMIT ?, ?";
            private static final String SELECT_BY_MNGR_SORT_BY_LASTNAME = "SELECT * FROM employee " +
                    "WHERE manager = ? ORDER BY lastname LIMIT ?, ?";
            private static final String SELECT_BY_MNGR_SORT_BY_HIREDATE = "SELECT * FROM employee " +
                    "WHERE manager = ? ORDER BY hiredate LIMIT ?, ?";
            private static final String SELECT_BY_MNGR_SORT_BY_SALARY = "SELECT * FROM employee " +
                    "WHERE manager = ? ORDER BY salary LIMIT ?, ?";
            private static final String GET_TOP_NTH_BY_DP = "SELECT * FROM employee " +
                    "WHERE department = ? ORDER BY salary DESC LIMIT ?, ?";

            @Override
            public List<Employee> getAllSortByHireDate(Paging paging) {
                return executeQuery(null, SORT_BY_HIREDATE, paging);
            }

            @Override
            public List<Employee> getAllSortByLastname(Paging paging) {
                return executeQuery(null, SORT_BY_LASTNAME, paging);
            }

            @Override
            public List<Employee> getAllSortBySalary(Paging paging) {
                return executeQuery(null, SORT_BY_SALARY, paging);
            }

            @Override
            public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {
                return executeQuery(null, SORT_BY_DP_AND_LASTNAME, paging);
            }

            @Override
            public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {
                return executeQuery(department.getId().intValue(),
                        SELECT_BY_DP_SORT_BY_HIREDATE, paging);
            }

            @Override
            public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {
                return executeQuery(department.getId().intValue(),
                        SELECT_BY_DP_SORT_BY_SALARY, paging);
            }

            @Override
            public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {
                return executeQuery(department.getId().intValue(),
                        SELECT_BY_DP_SORT_BY_LASTNAME, paging);
            }

            @Override
            public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {
                return executeQuery(manager.getId().intValue(),
                        SELECT_BY_MNGR_SORT_BY_LASTNAME, paging);
            }

            @Override
            public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {
                return executeQuery(manager.getId().intValue(),
                        SELECT_BY_MNGR_SORT_BY_HIREDATE, paging);
            }

            @Override
            public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {
                return executeQuery(manager.getId().intValue(),
                        SELECT_BY_MNGR_SORT_BY_SALARY, paging);
            }

            @Override
            public Employee getWithDepartmentAndFullManagerChain(Employee employee) {
                return getEmployeeByID(employee.getId().intValue(), true);
            }

            @Override
            public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
                return executeQuery(department.getId().intValue(),
                        (salaryRank - 1),
                        1,
                        GET_TOP_NTH_BY_DP)
                        .get(0);
            }

            private List<Employee> executeQuery(Integer firstParam, String query, Paging paging) {
                return executeQuery(firstParam, (paging.page - 1) * paging.itemPerPage, paging.itemPerPage, query);
            }

            private List<Employee> executeQuery(Integer firstParam, int offSet, int limit, String query) {
                try (PreparedStatement statement = getConnection().prepareStatement(query)) {
                    List<Employee> employees = new ArrayList<>();
                    int index = 1;

                    if (firstParam != null) {
                        statement.setInt(index++, firstParam);
                    }

                    statement.setInt(index++, offSet);
                    statement.setInt(index, limit);
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        employees.add(getEmployeeFromRS(rs, false, false));
                    }

                    return employees;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private Employee getEmployeeFromRS(ResultSet rs, boolean isManager, boolean needFullCain) throws SQLException {
                int managerID = rs.getInt("manager");
                return new Employee(
                        rs.getBigDecimal("id").toBigInteger(),
                        getFullName(rs),
                        getPosition(rs),
                        rs.getDate("hiredate").toLocalDate(),
                        rs.getBigDecimal("salary"),
                        needFullCain
                                ? getEmployeeByID(managerID, true)
                                : (isManager ? null : getEmployeeByID(managerID, false)),
                        getDepartment(rs.getInt("department"))
                );
            }

            public Employee getEmployeeByID(int id, boolean needFullChain) {
                if (id == 0) {
                    return null;
                }
                try (PreparedStatement statement = getConnection().prepareStatement(GET_EMPL_BY_ID)) {
                    statement.setInt(1, id);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        return getEmployeeFromRS(rs, !needFullChain, needFullChain);
                    }
                    return null;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private FullName getFullName(ResultSet resultSet) throws SQLException {
                return new FullName(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("middleName"));
            }

            private Position getPosition(ResultSet rs) throws SQLException {
                switch ((rs.getString("position")).toLowerCase()) {
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

            private Department getDepartment(int id) {
                try (PreparedStatement statement = getConnection().prepareStatement(GET_DP_BY_ID)) {
                    statement.setInt(1, id);
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        return new Department(
                                rs.getBigDecimal("id").toBigInteger(),
                                rs.getString("name"),
                                rs.getString("location")
                        );
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
    }
}
