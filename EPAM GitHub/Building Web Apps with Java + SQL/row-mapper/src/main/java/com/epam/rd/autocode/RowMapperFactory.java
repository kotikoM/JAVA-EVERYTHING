package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return new RowMapper<>() {
            @Override
            public Employee mapRow(ResultSet resultSet) {
                try {
                    BigInteger id = BigInteger.valueOf(resultSet.getInt("id"));
                    FullName fullName = getFullName(resultSet);
                    String position = resultSet.getString("position");
                    LocalDate hireDate = resultSet.getDate("hireDate").toLocalDate();
                    BigDecimal salary = resultSet.getBigDecimal("salary");

                    return new Employee(id, fullName, getPosition(position), hireDate, salary);
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

            private Position getPosition(String posiiton) throws SQLException {
                switch (posiiton.toLowerCase()) {
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
