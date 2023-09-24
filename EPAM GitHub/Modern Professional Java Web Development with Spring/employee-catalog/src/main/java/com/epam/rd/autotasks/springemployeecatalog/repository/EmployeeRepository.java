package com.epam.rd.autotasks.springemployeecatalog.repository;


import static com.epam.rd.autotasks.springemployeecatalog.constants.ColumnNames.*;
import static com.epam.rd.autotasks.springemployeecatalog.constants.SQLQueries.*;

import com.epam.rd.autotasks.springemployeecatalog.extractor.FullChainEmployeeExtractor;
import com.epam.rd.autotasks.springemployeecatalog.extractor.EmployeeExtractor;
import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;

@Repository
@SuppressWarnings("unused")
public class EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeExtractor emplExtractor;

    @Autowired
    private FullChainEmployeeExtractor fullChainEmplExtractor;

    public List<Employee> getAllEmployees(Pageable pageable) {
        Order order = getOrder(pageable);
        String query = String.format(SELECT_ALL_EMPLS_QUERY, order.getProperty(), order.getDirection());
        return getPageableEmployees(pageable, getEmployeesWithManager(query));
    }

    public Employee getEmployeeByID(long employeeID, boolean needFullChain) {
        String query = String.format(SELECT_EMPL_BY_ID_QUERY, employeeID);
        return getEmployeesWithManager(query, needFullChain).get(0);
    }

    public List<Employee> getEmployeesByMngrID(long mngrID, Pageable pageable) {
        Order order = getOrder(pageable);
        String query = String.format(SELECT_EMPLS_BY_MNGR_ID_QUERY, mngrID, order.getProperty(), order.getDirection());
        List<Employee> employeesWithManager = getEmployeesWithManager(query);
        return getPageableEmployees(pageable, filterManager(mngrID, employeesWithManager));
    }

    public List<Employee> getEmployeesByDepID(long depID, Pageable pageable) {
        Order order = getOrder(pageable);
        String query = String.format(SELECT_EMPLS_BY_DEP_ID_QUERY, depID, order.getProperty(), order.getDirection());
        List<Employee> employeesWithManager = getEmployeesWithManager(query);
        return getPageableEmployees(pageable, filterDepartment(depID, employeesWithManager));
    }

    public List<Employee> getEmployeesByDepName(String depName, Pageable pageable) {
        Order order = getOrder(pageable);
        String query = String.format(SELECT_EMPLS_BY_DEP_NAME_QUERY, depName, order.getProperty(), order.getDirection());
        List<Employee> employeesWithManager = getEmployeesWithManager(query);
        return getPageableEmployees(pageable, filterDepartment(depName, employeesWithManager));
    }

    private List<Employee> getEmployeesWithManager(String query, boolean needFullChain) {
        return jdbcTemplate.query(getPreparedStatementCreator(query),
                needFullChain ? fullChainEmplExtractor : emplExtractor);
    }

    private List<Employee> getEmployeesWithManager(String query) {
        return getEmployeesWithManager(query, Boolean.FALSE);
    }

    private List<Employee> filterManager(long mngrId, List<Employee> employees) {
        return employees.stream()
                .filter(empl -> empl.getManager() != null
                        && empl.getManager().getId().equals(mngrId))
                .collect(Collectors.toList());
    }

    private List<Employee> filterDepartment(String depName, List<Employee> employees) {
        return employees.stream()
                .filter(empl -> empl.getDepartment() != null
                        && empl.getDepartment().getName().equals(depName))
                .collect(Collectors.toList());
    }

    private List<Employee> filterDepartment(long depID, List<Employee> employees) {
        return employees.stream()
                .filter(empl -> empl.getDepartment() != null
                        && empl.getDepartment().getId().equals(depID))
                .collect(Collectors.toList());
    }

    private List<Employee> getPageableEmployees(Pageable pageable, List<Employee> employees) {
        int startIndex = getStartIndex(pageable);
        int endIndex = getEndIndex(pageable, employees, startIndex);
        if (startIndex > endIndex) {
            return Collections.emptyList();
        }
        return employees.subList(startIndex, endIndex);
    }

    private int getStartIndex(Pageable pageable) {
        return pageable.getPageNumber() *
                pageable.getPageSize();
    }

    private int getEndIndex(Pageable pageable, List<Employee> employees, int startIndex) {
        return Math.min(startIndex + pageable.getPageSize(), employees.size());
    }

    private PreparedStatementCreator getPreparedStatementCreator(String pageableQuery) {
        return connection -> connection.prepareStatement(
                pageableQuery,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
    }

    private Order getOrder(Pageable pageable) {
        return (!pageable.getSort().isEmpty()) ?
                pageable.getSort().toList().get(0) : Order.by(ID);
    }
}
