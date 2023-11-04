package com.agriculture.AgroPlanner.constants;

public class Endpoints {
    public static final String USERS_ENDPOINT = "/users";
    public static final String USER_ENDPOINT = "/{userID}";
    public static final String TASKS_ENDPOINT = "/tasks";
    public static final String USER_TASKS_ENDPOINT = "/users/{userID}";
    public static final String USER_COMPLETE_TASKS_ENDPOINT = "/users/{userID}/complete";
    public static final String USER_NOT_COMPLETE_TASKS_ENDPOINT = "/users/{userID}/not complete";
    public static final String TASK_ENDPOINT = "/{taskID}";
    public static final String FINANCES_ENDPOINT = "/finances";
    public static final String FINANCE_ENDPOINT = "/{recordID}";
    public static final String USER_FINANCIAL_RECORDS_ENDPOINT = "/users/{userID}";
    public static final String FINANCES_INCOMES_ENDPOINT = "/incomes";
    public static final String FINANCES_EXPENSES_ENDPOINT = "/expenses";
    public static final String FINANCE_STATS_ENDPOINT = "/stats";
    public static final String EMPLOYEES_ENDPOINT = "/employees";
    public static final String EMPLOYEE_ENDPOINT = "/{employeeID}";
    public static final String USER_EMPLOYEES_ENDPOINT = "/users/{userID}";
    public static final String CROPS_ENDPOINT = "/crops";
    public static final String CROP_ENDPOINT = "/{cropID}";
    public static final String HARVEST_ENDPOINT = "/harvest";
    public static final String HARVEST_ENTRY_ENDPOINT = "/{employeeID}/{cropID}";
    public static final String HARVEST_EMPLOYEE_ENDPOINT = "/employees/{employeeID}";
    public static final String HARVEST_CROP_ENDPOINT = "/crops/{cropID}";
    public static final String HARVEST_CROP_STATS_ENDPOINT = "/crops/stats";
    public static final String INVENTORY_ENDPOINT = "/inventory";
    public static final String INVENTORY_ITEM_ENDPOINT = "/{itemID}";
    public static final String INVENTORY_EMPLOYEE_ENDPOINT = "/employees/{employeeID}";
    public static final String WEATHER_ENDPOINT = "/weather/{lat}/{lon}";
}
