package com.agriculture.AgroPlanner.constants;

public class Queries {
    public static final String RETRIEVE_TASKS_BY_USERID = "SELECT t FROM Task t WHERE t.userID = :userID";
    public static final String RETRIEVE_TASKS_BY_USERID_AND_STATUS = "SELECT t FROM Task t WHERE t.userID = :userID AND t.status = :status";
    public static final String RETRIEVE_RECORDS_BY_USERID = "SELECT r FROM FinancialRecord r WHERE r.userID = :userID";
}
