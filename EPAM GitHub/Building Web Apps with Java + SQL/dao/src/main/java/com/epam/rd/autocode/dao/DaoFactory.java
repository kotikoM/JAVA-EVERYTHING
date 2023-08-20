package com.epam.rd.autocode.dao;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoFactory {
    private Connection getConnection() throws SQLException {
        return ConnectionSource.instance().createConnection();
    }

    public EmployeeDao employeeDAO() {
        return new EmployeeDao() {
            private static final String SELECT_BY_ID = "SELECT * FROM employee WHERE id = ?";
            private static final String SELECT_ALL = "SELECT * FROM employee";
            private static final String SELECT_BY_DEP_ID = "SELECT * FROM employee WHERE department = ?";
            private static final String SELECT_BY_MGR_ID = "SELECT * FROM employee WHERE manager = ?";
            private static final String INSERT_EMPLOY = "INSERT INTO employee (id, firstName, lastName, middleName, position, manager, hireDate, salary, department) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            private static final String DELETE_EMPLOY = "DELETE FROM employee WHERE id = ?";


            @Override
            public List<Employee> getByDepartment(Department department) {
                List<Employee> employees = new ArrayList<>();

                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_DEP_ID)) {
                    statement.setInt(1, department.getId().intValue());
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        employees.add(getEmployee(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return employees;
            }

            @Override
            public List<Employee> getByManager(Employee employee) {
                List<Employee> employees = new ArrayList<>();

                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_MGR_ID)) {
                    statement.setInt(1, employee.getId().intValue());
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        employees.add(getEmployee(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return employees;
            }

            @Override
            public Optional<Employee> getById(BigInteger Id) {
                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
                    statement.setInt(1, Id.intValue());
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        return Optional.of(getEmployee(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return Optional.empty();
            }

            @Override
            public List<Employee> getAll() {
                List<Employee> employees = new ArrayList<>();

                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        employees.add(getEmployee(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return employees;
            }

            @Override
            public Employee save(Employee employee) {
                try (PreparedStatement statement = getConnection().prepareStatement(INSERT_EMPLOY)) {
                    //(1-id, 2-firstName, 3-lastName, 4-middleName, 5-position,
                    // 6-manager, 7-hireDate, 8-salary, 9-department)
                    statement.setInt(1, employee.getId().intValue());
                    statement.setString(2, employee.getFullName().getFirstName());
                    statement.setString(3, employee.getFullName().getLastName());
                    statement.setString(4, employee.getFullName().getMiddleName());
                    statement.setString(5, employee.getPosition().toString());
                    statement.setInt(6, employee.getManagerId().intValue());
                    statement.setDate(7, Date.valueOf(employee.getHired()));
                    statement.setInt(8, employee.getSalary().intValue());
                    statement.setInt(9, employee.getDepartmentId().intValue());
                    statement.executeUpdate();

                    return employee;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void delete(Employee employee) {
                try (PreparedStatement statement = getConnection().prepareStatement(DELETE_EMPLOY)) {
                    statement.setInt(1, employee.getId().intValue());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private Employee getEmployee(ResultSet rs) throws SQLException {
                return new Employee(
                        rs.getBigDecimal("id").toBigInteger(),
                        getFullName(rs),
                        getPosition(rs),
                        rs.getDate("hireDate").toLocalDate(),
                        rs.getBigDecimal("salary"),
                        BigInteger.valueOf(rs.getLong("manager")),
                        BigInteger.valueOf(rs.getLong("department"))
                );
            }

            private FullName getFullName(ResultSet resultSet) throws SQLException {
                return new FullName(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("middleName"));
            }

            private Position getPosition(ResultSet rs) throws SQLException {
                switch (rs.getString("position").toLowerCase()) {
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

    public DepartmentDao departmentDAO() {
        return new DepartmentDao() {
            private static final String SELECT_BY_ID = "SELECT * FROM department WHERE id = ?";
            private static final String SELECT_ALL = "SELECT * FROM department";
            private static final String INSERT_DEP = "INSERT INTO department (id, name, location) VALUES (?, ?, ?)";
            private static final String DELETE_DEP = "DELETE FROM department WHERE id = ?";

            @Override
            public Optional<Department> getById(BigInteger Id) {
                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID)) {
                    statement.setInt(1, Id.intValue());
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        return Optional.of(getDepartment(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return Optional.empty();
            }

            @Override
            public List<Department> getAll() {
                List<Department> departments = new ArrayList<>();

                try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        departments.add(getDepartment(rs));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return departments;
            }

            @Override
            public Department save(Department department) {
                if (getById(department.getId()).isEmpty()) {
                    try (PreparedStatement statement = getConnection().prepareStatement(INSERT_DEP)) {
                        //(1-id, 2-name, 3-location)
                        statement.setInt(1, department.getId().intValue());
                        statement.setString(2, department.getName());
                        statement.setString(3, department.getLocation());
                        statement.executeUpdate();

                        return department;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    delete(department);
                    save(department);
                }
                return department;
            }

            @Override
            public void delete(Department department) {
                try (PreparedStatement statement = getConnection().prepareStatement(DELETE_DEP)) {
                    statement.setInt(1, department.getId().intValue());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            private Department getDepartment(ResultSet rs) throws SQLException {
                return new Department(BigInteger.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("location"));
            }
        };
    }
}
