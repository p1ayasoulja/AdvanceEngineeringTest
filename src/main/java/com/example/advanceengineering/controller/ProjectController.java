package com.example.advanceengineering.controller;

import com.example.advanceengineering.ReqRes.*;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ApiOperation("Create project")
    public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        Project project = projectService.create(createProjectRequest.getName());
        CreateProjectResponse createProjectResponse = new CreateProjectResponse(project.getName());
        return ResponseEntity.ok(createProjectResponse);
    }

    @GetMapping
    @ApiOperation("Получить список всех проектов")
    public ResponseEntity<List<GetProjectResponse>> getProjects() {
        List<Project> projects = projectService.getProjects();
        List<GetProjectResponse> getProjectResponse = new ArrayList<>();
        projects.forEach(project -> getProjectResponse.add(new GetProjectResponse(project.getName())));
        return ResponseEntity.ok(getProjectResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> DeleteProject(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<UpdateProjectResponse> updateProject(@PathVariable("id") Long id, @RequestBody UpdateProjectRequest updateProjectRequest) {
        Project project = projectService.updateProject(id, updateProjectRequest.getName(), updateProjectRequest.getParent_id());
        UpdateProjectResponse updateProjectResponse = new UpdateProjectResponse(project.getName());
        return ResponseEntity.ok(updateProjectResponse);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GetProjectResponse> getProject(@PathVariable Long id) {
        Optional<Project> project = projectService.getProject(id);
        GetProjectResponse getProjectResponse = new GetProjectResponse(project.get().getName());
        return ResponseEntity.ok(getProjectResponse);
    }
}
