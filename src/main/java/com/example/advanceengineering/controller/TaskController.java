package com.example.advanceengineering.controller;

import com.example.advanceengineering.ReqRes.CreateTaskRequest;
import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.ReqRes.UpdateTaskRequest;
import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.service.ProjectService;
import com.example.advanceengineering.service.TaskService;
import org.springframework.http.ResponseEntity;
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
    //todo is user author?
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTasksResponse> updateTask(@PathVariable("id") Long id,
                                                       @RequestBody UpdateTaskRequest updateTaskRequest) {
        Task task = taskService.getTask(id).get();
        taskService.updateTask(task, updateTaskRequest.getName(),
                updateTaskRequest.getTitle(), updateTaskRequest.getStatus(),
                updateTaskRequest.getType());

        GetTasksResponse getTasksResponse = new GetTasksResponse(task.getName(), task.getTitle(), task.getCreateTime(), task.getStatusChangeTime(),
                task.getStatus(), task.getType(), task.getName());
        return ResponseEntity.ok(getTasksResponse);
    }

    @PostMapping
    public ResponseEntity<GetTasksResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        Task task = taskService.createTask(createTaskRequest.getName(), createTaskRequest.getTitle(),
                LocalDate.now(), LocalDate.now(),
                createTaskRequest.getStatus(),
                //TODO username
                createTaskRequest.getType(), projectService.getProject(createTaskRequest.getProject_id()).get());
        GetTasksResponse getTasksResponse = new GetTasksResponse(task.getName(), task.getTitle(), task.getCreateTime(), task.getStatusChangeTime(), task.getStatus(), task.getType(), task.getName());
        return ResponseEntity.ok(getTasksResponse);
    }
}
