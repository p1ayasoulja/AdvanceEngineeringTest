package com.example.advanceengineering.controller;

import com.example.advanceengineering.ReqRes.*;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.service.ProjectService;
import com.example.advanceengineering.service.TaskService;
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
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
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
    public ResponseEntity<List<GetProjectNames>> getProjects() {
        List<Project> projects = projectService.getProjects();
        List<GetProjectNames> getProjectNames = new ArrayList<>();
        projects.forEach(project -> {
            List<Task> tasks = taskService.getTasksByProject(project);
            List<GetTasksResponse> getTasksResponses = new ArrayList<>();
            taskService.sortTasks(tasks, getTasksResponses);
            if (!projectService.hasParent(project)) {
                getProjectNames.add(new GetProjectNames(project.getName()));
            }
        });
        return ResponseEntity.ok(getProjectNames);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> DeleteProject(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ResponseEntity.ok("Project deleted");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateProjectResponse> updateProject(@PathVariable("id") Long id, @RequestBody UpdateProjectRequest updateProjectRequest) {
        Project project = projectService.updateProject(id, updateProjectRequest.getName(), updateProjectRequest.getParent_id());
        UpdateProjectResponse updateProjectResponse = new UpdateProjectResponse(project.getName());
        return ResponseEntity.ok(updateProjectResponse);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GetProjectInfoResponse> getProjectInfo(@PathVariable Long id) {
        Optional<Project> project = projectService.getProject(id);

        List<Project> subProjects = projectService.subProjects(project.get());
        List<GetProjectNames> getSubProjects = new ArrayList<>();
        projectService.refactorProjects(subProjects, getSubProjects);

        List<Task> tasks = taskService.getTasksByProject(project.get());
        List<GetTasksResponse> getTasksResponses = new ArrayList<>();
        taskService.sortTasks(tasks, getTasksResponses);

        GetProjectInfoResponse getProjectInfoResponse = new GetProjectInfoResponse(project.get().getName(), getTasksResponses,
                getSubProjects);
        return ResponseEntity.ok(getProjectInfoResponse);
    }
}
