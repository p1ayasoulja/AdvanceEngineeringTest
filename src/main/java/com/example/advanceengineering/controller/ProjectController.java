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
    @ApiOperation("Создать проект")
    public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        Project project = projectService.create(createProjectRequest.getName());
        CreateProjectResponse createProjectResponse = new CreateProjectResponse(project.getName());
        return ResponseEntity.ok(createProjectResponse);
    }

    @GetMapping
    @ApiOperation("Получить список всех проектов")
    public ResponseEntity<List<GetProjectsResponse>> getProjects() {
        List<Project> projects = projectService.getProjects();
        List<GetProjectsResponse> getProjectResponse = new ArrayList<>();
        projects.forEach(project -> {
            if (!projectService.hasParent(project)) {
                getProjectResponse.add(new GetProjectsResponse(project.getName(), project.getTasks()));
            }
        });
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
    public ResponseEntity<GetProjectInfoResponse> getProjectInfo(@PathVariable Long id) {
        Optional<Project> project = projectService.getProject(id);
        List<Project> projects = projectService.subProjects(project.get());
        List<GetProjectsResponse> getProjectsResponses = new ArrayList<>();
        projects.forEach(project1 -> {
            getProjectsResponses.add(new GetProjectsResponse(project1.getName(), project1.getTasks()));
        });
        GetProjectInfoResponse getProjectInfoResponse = new GetProjectInfoResponse(project.get().getName(),
                project.get().getTasks(),
                getProjectsResponses);
        return ResponseEntity.ok(getProjectInfoResponse);
    }
}
