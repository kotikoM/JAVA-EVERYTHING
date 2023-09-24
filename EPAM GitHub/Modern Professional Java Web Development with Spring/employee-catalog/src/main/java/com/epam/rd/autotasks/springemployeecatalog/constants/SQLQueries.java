package com.epam.rd.autotasks.springemployeecatalog.constants;

public class SQLQueries {
    public static final String SELECT_ALL_EMPLS_QUERY =
            "SELECT e.id, e.firstName, e.lastName, e.middleName, e.position, e.manager, e.hireDate AS hired, e.salary, " +
                    "e.department, d.name, d.location " +
                    "FROM employee e " +
                    "LEFT JOIN department d ON e.department = d.id " +
                    "ORDER BY %s %s";
    public static final String SELECT_EMPL_BY_ID_QUERY =
            "WITH RECURSIVE EmplHierarchy (id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location) AS " +
                    "(SELECT e.id, e.firstName, e.lastName, e.middleName, e.position, e.manager, e.hireDate AS hired, e.salary, e.department " +
                    "FROM employee e WHERE id = %d" +
                    "UNION ALL " +
                    "SELECT m.id, m.firstName, m.lastName, m.middleName, m.position, m.manager, m.hireDate AS hired, m.salary, m.department " +
                    "FROM employee m " +
                    "INNER JOIN EmplHierarchy ON EmplHierarchy.manager = m.id) " +
                    "SELECT * FROM EmplHierarchy " +
                    "LEFT JOIN department d ON EmplHierarchy.department = d.id";

    public static final String SELECT_EMPLS_BY_MNGR_ID_QUERY =
            "WITH RECURSIVE EmplHierarchy (id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location) AS " +
                    "(SELECT e.id, e.firstName, e.lastName, e.middleName, e.position, e.manager, e.hireDate AS hired, e.salary, e.department " +
                    "FROM employee e WHERE manager = %d " +
                    "UNION ALL " +
                    "SELECT m.id, m.firstName, m.lastName, m.middleName, m.position, m.manager, m.hireDate AS hired, m.salary, m.department " +
                    "FROM employee m " +
                    "INNER JOIN EmplHierarchy ON EmplHierarchy.manager = m.id) " +
                    "SELECT * FROM EmplHierarchy " +
                    "LEFT JOIN department d ON EmplHierarchy.department = d.id " +
                    "ORDER BY %S %S";

    public static final String SELECT_EMPLS_BY_DEP_ID_QUERY =
            "WITH RECURSIVE EmplHierarchy (id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location) AS " +
                    "(SELECT e.id, e.firstName, e.lastName, e.middleName, e.position, e.manager, e.hireDate AS hired, e.salary, e.department " +
                    "FROM employee e WHERE department = %d " +
                    "UNION ALL " +
                    "SELECT m.id, m.firstName, m.lastName, m.middleName, m.position, m.manager, m.hireDate AS hired, m.salary, m.department " +
                    "FROM employee m " +
                    "INNER JOIN EmplHierarchy ON EmplHierarchy.manager = m.id) " +
                    "SELECT DISTINCT EmplHierarchy.id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location " +
                    "FROM EmplHierarchy " +
                    "LEFT JOIN department d ON EmplHierarchy.department = d.id " +
                    "ORDER BY %S %S";

    public static final String SELECT_EMPLS_BY_DEP_NAME_QUERY =
            "WITH RECURSIVE EmplHierarchy (id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location) AS " +
                    "(SELECT e.id, e.firstName, e.lastName, e.middleName, e.position, e.manager, e.hireDate AS hired, e.salary, e.department " +
                    "FROM employee e " +
                    "LEFT JOIN department d ON e.department = d.id " +
                    "WHERE d.name = '%s' " +
                    "UNION ALL " +
                    "SELECT m.id, m.firstName, m.lastName, m.middleName, m.position, m.manager, m.hireDate AS hired, m.salary, m.department " +
                    "FROM employee m " +
                    "INNER JOIN EmplHierarchy ON EmplHierarchy.manager = m.id) " +
                    "SELECT DISTINCT EmplHierarchy.id, firstName, lastName, middleName, position, manager, hired, salary, department, name, location " +
                    "FROM EmplHierarchy " +
                    "LEFT JOIN department d ON EmplHierarchy.department = d.id " +
                    "ORDER BY %s %s";

}
