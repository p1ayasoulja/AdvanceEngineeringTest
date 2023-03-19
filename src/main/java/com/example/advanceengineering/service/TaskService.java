package com.example.advanceengineering.service;

import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.entity.Task;
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

    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    //TODO проработать optional
    public Task updateTask(Task task, String name, String title,
                           Task.Status status,
                           Task.Type type) {
        if (name != null) {
            task.setName(name);
        }
        if (title != null) {
            task.setTitle(title);
        }
        if (status != null) {
            task.setStatus(status);
            task.setStatusChangeTime(LocalDate.now());
        }
        if (type != null) {
            task.setType(type);
        }
        return taskRepo.save(task);

    }

    public Task createTask(String name, String title, LocalDate createTime,
                           LocalDate statusChangeTime, Task.Status status,
                           Task.Type type, Project project) {
        Task task = new Task(name, title, createTime, statusChangeTime, status, type, project);
        return taskRepo.save(task);
    }
}

