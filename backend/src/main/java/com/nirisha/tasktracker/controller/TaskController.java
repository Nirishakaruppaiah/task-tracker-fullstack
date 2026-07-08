
package com.nirisha.tasktracker.controller;

import com.nirisha.tasktracker.dto.TaskRequestDTO;
import com.nirisha.tasktracker.entity.Task;
import com.nirisha.tasktracker.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CREATE TASK
    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody TaskRequestDTO taskRequest) {

        Task savedTask = taskService.saveTask(taskRequest);

        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // GET ALL TASKS
    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(

            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Task> tasks = taskService.getAllTasks(
                status,
                priority,
                page,
                size,
                sortBy);

        return ResponseEntity.ok(tasks);
    }

    // GET TASK BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);
    }

    // UPDATE TASK
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO taskRequest) {

        Task updatedTask = taskService.updateTask(id, taskRequest);

        return ResponseEntity.ok(updatedTask);
    }

    // DELETE TASK
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
