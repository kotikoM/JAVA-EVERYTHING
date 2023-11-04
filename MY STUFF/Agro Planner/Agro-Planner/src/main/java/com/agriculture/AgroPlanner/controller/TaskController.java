package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.Task;
import com.agriculture.AgroPlanner.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.agriculture.AgroPlanner.constants.ColumnNames.TASK_COMPLETE;
import static com.agriculture.AgroPlanner.constants.ColumnNames.TASK_NOT_COMPLETE;
import static com.agriculture.AgroPlanner.constants.Endpoints.*;


@RestController
@RequestMapping(TASKS_ENDPOINT)
@SuppressWarnings("unused")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping
    public ResponseEntity<Task> insertTask(
            @RequestBody Task task) {
        return taskService.createUser(task);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> retrieveAllTasks(Pageable pageable) {
        return taskService.retrieveAllTasks(pageable);
    }

    @GetMapping(TASK_ENDPOINT)
    public ResponseEntity<Task> retrieveTask(
            @PathVariable Long taskID) {
        return taskService.retrieveTask(taskID);
    }

    @DeleteMapping(TASK_ENDPOINT)
    public void deleteTask(
            @PathVariable Long taskID) {
        taskService.deleteTask(taskID);
    }

    @GetMapping(USER_TASKS_ENDPOINT)
    public ResponseEntity<Page<Task>> retrieveAllUserTasks(Pageable pageable,
                                                           @PathVariable Long userID) {
        return taskService.retrieveAllUserTasks(pageable, userID);
    }

    @GetMapping(USER_COMPLETE_TASKS_ENDPOINT)
    public ResponseEntity<Page<Task>> retrieveAllUserCompletedTasks(Pageable pageable,
                                                                    @PathVariable Long userID) {
        return taskService.retrieveAllUserTasksByStatus(pageable, userID, TASK_COMPLETE);
    }

    @GetMapping(USER_NOT_COMPLETE_TASKS_ENDPOINT)
    public ResponseEntity<Page<Task>> retrieveAllUserNotCompletedTasks(Pageable pageable,
                                                                       @PathVariable Long userID) {
        return taskService.retrieveAllUserTasksByStatus(pageable, userID, TASK_NOT_COMPLETE);
    }

    @PatchMapping(TASK_ENDPOINT)
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long taskID,
            @RequestBody Map<String, String> updates) {
        return taskService.updateStatus(taskID, updates);
    }
}
