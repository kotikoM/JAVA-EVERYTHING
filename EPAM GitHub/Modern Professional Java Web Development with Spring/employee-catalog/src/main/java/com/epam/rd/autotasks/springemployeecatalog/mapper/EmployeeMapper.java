package com.epam.rd.autotasks.springemployeecatalog.mapper;


import static com.epam.rd.autotasks.springemployeecatalog.constants.ColumnNames.*;
import static java.sql.Types.NULL;

import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.FullName;
import com.epam.rd.autotasks.springemployeecatalog.domain.Position;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class EmployeeMapper {
    public Employee mapRow(ResultSet rs, boolean needFullChain) throws SQLException {
        return getEmployee(rs, getManager(rs, needFullChain));
    }

    private Employee getEmployee(ResultSet rs, Employee manager) throws SQLException {
        long id = rs.getLong(ID);
        FullName fullName = getFullName(rs);
        Position position = Position.valueOf(rs.getString(POSITION));
        LocalDate hireDate = rs.getDate(HIRE_DATE).toLocalDate();
        BigDecimal salary = rs.getBigDecimal(SALARY);
        Department department = rs.getLong(DEPARTMENT) == NULL ? null : getDepartment(rs);

        return new Employee(id, fullName, position, hireDate, salary, manager, department);
    }

    private Employee getManager(ResultSet rs, boolean needFullChain) throws SQLException {
        int currentRow = rs.getRow();
        long managerId = rs.getLong(MANAGER);
        Employee manager = null;
        rs.beforeFirst();

        while (rs.next()) {
            if (rs.getLong(ID) == managerId) {
                manager = getEmployee(rs,
                        needFullChain ? getManager(rs, Boolean.TRUE) : null);
                break;
            }
        }

        rs.absolute(currentRow);
        return manager;
    }

    private FullName getFullName(ResultSet rs) throws SQLException {
        return new FullName(
                rs.getString(FIRST_NAME),
                rs.getString(LAST_NAME),
                rs.getString(MIDDLE_NAME)
        );
    }

    private Department getDepartment(ResultSet rs) throws SQLException {
        return new Department(
                rs.getLong(DEP_ID),
                rs.getString(DEP_NAME),
                rs.getString(DEP_LOCATION));
    }
}
