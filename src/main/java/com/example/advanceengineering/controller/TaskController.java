package com.example.advanceengineering.controller;

import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping()
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
////        Task newTask = taskService.create(task.getName(), task.getTitle(), LocalDate.now(), LocalDate.now(), task.getUser(), Task.Status.New, task.getType(), task.getProject());
//        Task newTask = taskService.createEZ(task.getName(), task.getTitle());
//        return ResponseEntity.ok(newTask);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTasksResponse> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            GetTasksResponse getTasksResponse = new GetTasksResponse(task.get().getName(),
                    task.get().getTitle(), LocalDate.now(), LocalDate.now(),
                    task.get().getStatus(), task.get().getType(),
                    task.get().getUser().getUsername());
            return ResponseEntity.ok(getTasksResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
