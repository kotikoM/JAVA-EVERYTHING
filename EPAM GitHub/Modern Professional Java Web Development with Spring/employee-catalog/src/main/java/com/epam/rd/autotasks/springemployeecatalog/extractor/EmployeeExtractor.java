package com.epam.rd.autotasks.springemployeecatalog.extractor;


import com.epam.rd.autotasks.springemployeecatalog.mapper.EmployeeMapper;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Component
@SuppressWarnings("unused")
public class EmployeeExtractor implements ResultSetExtractor<List<Employee>> {
    @Autowired
    private EmployeeMapper emplMapper;

    @Override
    public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(emplMapper.mapRow(resultSet, Boolean.FALSE));
        }
        return employees;
    }
}
