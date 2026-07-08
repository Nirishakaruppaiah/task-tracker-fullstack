package com.nirisha.tasktracker.service;

import com.nirisha.tasktracker.dto.TaskRequestDTO;
import com.nirisha.tasktracker.entity.Project;
import com.nirisha.tasktracker.entity.Task;
import com.nirisha.tasktracker.exception.ResourceNotFoundException;
import com.nirisha.tasktracker.repository.ProjectRepository;
import com.nirisha.tasktracker.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Get All Tasks
    public Page<Task> getAllTasks(String status,
                                  String priority,
                                  int page,
                                  int size,
                                  String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return taskRepository
                .findByStatusContainingIgnoreCaseAndPriorityContainingIgnoreCase(
                        status,
                        priority,
                        pageable
                );
    }

    // Create Task
    public Task saveTask(TaskRequestDTO request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        return taskRepository.save(task);
    }

    // Update Task
    public Task updateTask(Long id, TaskRequestDTO request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        return taskRepository.save(task);
    }

    // Get Task By ID
    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    // Delete Task
    public String deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);

        return "Task deleted successfully";
    }
}