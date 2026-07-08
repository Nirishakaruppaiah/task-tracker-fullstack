package com.nirisha.tasktracker.service;

import com.nirisha.tasktracker.dto.TaskRequestDTO;
import com.nirisha.tasktracker.entity.Project;
import com.nirisha.tasktracker.entity.Task;
import com.nirisha.tasktracker.repository.ProjectRepository;
import com.nirisha.tasktracker.repository.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {

        Project project = new Project();
        project.setId(1L);

        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitle("Learn Spring Boot");
        request.setDescription("Mockito Testing");
        request.setStatus("Pending");
        request.setPriority("High");
        request.setDueDate(LocalDate.of(2026, 7, 10));
        request.setProjectId(1L);

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task savedTask = taskService.saveTask(request);

        assertNotNull(savedTask);
        assertEquals("Learn Spring Boot", savedTask.getTitle());
        assertEquals("Pending", savedTask.getStatus());
        assertEquals(project, savedTask.getProject());

        verify(projectRepository).findById(1L);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testGetTaskById() {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Backend Testing");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        Task foundTask = taskService.getTaskById(1L);

        assertNotNull(foundTask);
        assertEquals(1L, foundTask.getId());
        assertEquals("Backend Testing", foundTask.getTitle());

        verify(taskRepository).findById(1L);
    }

    @Test
    void testDeleteTask() {

        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        String result = taskService.deleteTask(1L);

        assertEquals("Task deleted successfully", result);

        verify(taskRepository).findById(1L);
        verify(taskRepository).delete(task);
    }

}