
package com.nirisha.tasktracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirisha.tasktracker.dto.TaskRequestDTO;
import com.nirisha.tasktracker.entity.Task;
import com.nirisha.tasktracker.exception.ResourceNotFoundException;
import com.nirisha.tasktracker.service.TaskService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    // ----------------------------
    // CREATE TASK
    // ----------------------------
    @Test
    void testCreateTask() throws Exception {

        TaskRequestDTO request = new TaskRequestDTO();
        request.setTitle("Testing");
        request.setDescription("Controller Test");
        request.setStatus("Pending");
        request.setPriority("High");
        request.setDueDate(LocalDate.now().plusDays(1));
        request.setProjectId(1L);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Testing");

        when(taskService.saveTask(any(TaskRequestDTO.class)))
                .thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Testing"));
    }

    // ----------------------------
    // GET TASK BY ID
    // ----------------------------
    @Test
    void testGetTaskById() throws Exception {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Backend");

        when(taskService.getTaskById(1L))
                .thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Backend"));
    }

    // ----------------------------
    // GET ALL TASKS
    // ----------------------------
    @Test
    void testGetAllTasks() throws Exception {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Spring Boot");

        when(taskService.getAllTasks("", "", 0, 10, "id"))
                .thenReturn(new PageImpl<>(List.of(task)));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title")
                        .value("Spring Boot"));
    }

    // ----------------------------
    // VALIDATION TEST
    // ----------------------------
    @Test
    void testCreateTaskValidationFailure() throws Exception {

        TaskRequestDTO request = new TaskRequestDTO();

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // ----------------------------
    // RESOURCE NOT FOUND TEST
    // ----------------------------
    @Test
    void testGetTaskByIdNotFound() throws Exception {

        when(taskService.getTaskById(100L))
                .thenThrow(new ResourceNotFoundException("Task not found"));

        mockMvc.perform(get("/tasks/100"))
                .andExpect(status().isNotFound());
    }
}

