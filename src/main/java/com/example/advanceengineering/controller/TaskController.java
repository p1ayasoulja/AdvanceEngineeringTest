package com.example.advanceengineering.controller;

import com.example.advanceengineering.ReqRes.UpdateTaskRequest;
import com.example.advanceengineering.ReqRes.CreateTaskRequest;
import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.entity.Role;
import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.entity.User;
import com.example.advanceengineering.service.ProjectService;
import com.example.advanceengineering.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Получить задачу")
    public ResponseEntity<GetTasksResponse> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            GetTasksResponse getTasksResponse = new GetTasksResponse(task.get().getName(),
                    task.get().getTitle(), task.get().getCreateTime(), task.get().getStatusChangeTime(),
                    task.get().getStatus(), task.get().getType(),
                    task.get().getUser().getUsername());
            return ResponseEntity.ok(getTasksResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @ApiOperation("Получить все задачи")
    public ResponseEntity<List<GetTasksResponse>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        List<GetTasksResponse> getTasksResponses = new ArrayList<>();
        tasks.forEach(task -> {
            getTasksResponses.add(new GetTasksResponse(task.getName(),
                    task.getTitle(), task.getCreateTime(), task.getStatusChangeTime(),
                    task.getStatus(), task.getType(),
                    task.getUser().getUsername()));
        });
        return ResponseEntity.ok(getTasksResponses);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить задачу")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id,
                                        @AuthenticationPrincipal User user) {

        if (taskService.canDelete(id, user)) {
            return ResponseEntity.ok("Deleted");
        }

        return ResponseEntity.ok("You are not a owner");
    }

    @PutMapping("/{id}")
    @ApiOperation("Обновить задачу")
    public ResponseEntity<?> updateTask(@PathVariable("id") Long id,
                                             @RequestBody UpdateTaskRequest updateTaskRequest,
                                             @AuthenticationPrincipal User user) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            if (user.getRoles().contains(Role.ADMIN)) {
                taskService.adminUpdateTask(task.get(), updateTaskRequest.getName(),
                        updateTaskRequest.getTitle(), updateTaskRequest.getStatus(),
                        updateTaskRequest.getType());
            } else {
                taskService.userUpdateTask(task.get(), updateTaskRequest.getStatus());
            }
            GetTasksResponse getTasksResponse = new GetTasksResponse(task.get().getName(), task.get().getTitle(), task.get().getCreateTime(), task.get().getStatusChangeTime(),
                    task.get().getStatus(), task.get().getType(), task.get().getName());
            return ResponseEntity.ok(getTasksResponse);
        } else return ResponseEntity.ok("Task is not present");
    }
    @PostMapping
    @ApiOperation("Создать задачу")
    public ResponseEntity<GetTasksResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest,
                                                       @AuthenticationPrincipal User user) {
        Task task = taskService.createTask(createTaskRequest.getName(), createTaskRequest.getTitle(),
                LocalDate.now(), LocalDate.now(), user,
                createTaskRequest.getStatus(),
                createTaskRequest.getType(), projectService.getProject(createTaskRequest.getProject_id()).get());
        GetTasksResponse getTasksResponse = new GetTasksResponse(task.getName(), task.getTitle(), task.getCreateTime(), task.getStatusChangeTime(), task.getStatus(), task.getType(), user.getUsername());
        return ResponseEntity.ok(getTasksResponse);
    }
}
