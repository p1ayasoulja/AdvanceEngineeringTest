package com.example.advanceengineering.controller;

import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//        Task newTask = taskService.create(task.getName(), task.getTitle(), LocalDate.now(), LocalDate.now(), task.getUser(), Task.Status.New, task.getType(), task.getProject());
        Task newTask = taskService.createEZ(task.getName(), task.getTitle());
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> taskList = taskService.getTasks();
        return ResponseEntity.ok(taskList);
    }
}
