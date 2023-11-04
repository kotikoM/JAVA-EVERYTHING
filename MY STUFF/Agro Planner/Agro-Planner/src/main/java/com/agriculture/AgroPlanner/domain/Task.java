package com.agriculture.AgroPlanner.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Entity
@SuppressWarnings("unused")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TASK_TASKID)
    private Long taskID;
    @Column(name = TASK_TASKNAME)
    private String taskName;

    @Column(name = TASK_TASKDESC)
    private String taskDesc;

    @Column(name = TASK_DUEDATE)
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @Column(name = TASK_STATUS)
    private String status;

    @Column(name = TASK_USERID)
    private Long userID;

    public Task(Long taskID, String taskName, String taskDesc, LocalDate dueDate, String status, Long userID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.status = status;
        this.userID = userID;
    }

    public Task() {
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
