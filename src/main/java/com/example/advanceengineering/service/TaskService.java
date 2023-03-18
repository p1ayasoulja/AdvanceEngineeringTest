package com.example.advanceengineering.service;

import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.entity.Task;
import com.example.advanceengineering.entity.User;
import com.example.advanceengineering.repos.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task create(String name, String title, LocalDate date, LocalDate change, User user, Task.Status status, Task.Type type, Project project) {
        Task task = new Task(name, title, date, change, user, status, type, project);
        return taskRepo.save(task);

    }

    public Task createEZ(String name, String title) {
        Task task = new Task(name, title);
        return taskRepo.save(task);
    }

    public List<Task> getTasksByProject(Project project) {
        return taskRepo.findByProject(project);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }

    public List<GetTasksResponse> sortTasks(List<Task> tasks, List<GetTasksResponse> getTasksResponses) {
        tasks.forEach(task -> getTasksResponses.add(new GetTasksResponse(task.getName(),
                task.getTitle(), LocalDate.now(), LocalDate.now(),
                task.getStatus(), task.getType(),
                task.getUser().getUsername())));
        return getTasksResponses;
    }
}
