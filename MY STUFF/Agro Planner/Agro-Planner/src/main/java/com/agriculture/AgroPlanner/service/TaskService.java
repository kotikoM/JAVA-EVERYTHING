package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TaskService {
    ResponseEntity<Task> createUser(Task task);

    ResponseEntity<Page<Task>> retrieveAllTasks(Pageable pageable);

    ResponseEntity<Task> retrieveTask(Long taskID);

    void deleteTask(Long taskID);

    ResponseEntity<Page<Task>> retrieveAllUserTasks(Pageable pageable, Long userID);

    ResponseEntity<Page<Task>> retrieveAllUserTasksByStatus(Pageable pageable, Long userID, String status);

    ResponseEntity<Task> updateStatus(Long taskID, Map<String, String> updates);
}
