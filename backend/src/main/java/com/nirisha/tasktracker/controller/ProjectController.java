
package com.nirisha.tasktracker.controller;

import com.nirisha.tasktracker.entity.Project;
import com.nirisha.tasktracker.service.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // CREATE PROJECT
    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project) {

        Project savedProject = projectService.saveProject(project);

        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    // GET ALL PROJECTS
    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {

        return ResponseEntity.ok(
                projectService.getAllProjects()
        );
    }

    // GET PROJECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long id) {

        Project project = projectService.getProjectById(id);

        return ResponseEntity.ok(project);
    }

    // DELETE PROJECT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }
}

