package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.Task;
import com.agriculture.AgroPlanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.agriculture.AgroPlanner.constants.ColumnNames.*;

@Service
@SuppressWarnings("unused")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private static ResponseEntity<Page<Task>> getPageResponseEntity(Page<Task> tasks) {
        return (tasks.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(tasks);
    }

    public ResponseEntity<Task> createUser(Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    public ResponseEntity<Page<Task>> retrieveAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return getPageResponseEntity(tasks);
    }

    public ResponseEntity<Task> retrieveTask(Long taskID) {
        Optional<Task> optionalTask = taskRepository.findById(taskID);
        return optionalTask.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    public void deleteTask(Long taskID) {
        taskRepository.deleteById(taskID);
    }

    public ResponseEntity<Page<Task>> retrieveAllUserTasks(Pageable pageable, Long userID) {
        Page<Task> tasks = taskRepository.findAllUserTasks(pageable, userID);
        return getPageResponseEntity(tasks);
    }

    public ResponseEntity<Page<Task>> retrieveAllUserTasksByStatus(Pageable pageable, Long userID, String status) {
        Page<Task> tasks = taskRepository.findAllUserTasksByType(pageable, userID, status);
        return getPageResponseEntity(tasks);
    }

    public ResponseEntity<Task> updateStatus(Long taskID, Map<String, String> updates) {
        Task existingTask = taskRepository.findById(taskID)
                .orElse(null);

        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        String status = updates.get(TASK_STATUS);
        if (!(status.equals(TASK_COMPLETE) || status.equals(TASK_NOT_COMPLETE))) {
            return ResponseEntity.badRequest().build();
        }

        existingTask.setStatus(status);
        Task updatedTask = taskRepository.save(existingTask);
        return ResponseEntity.ok(updatedTask);
    }
}
