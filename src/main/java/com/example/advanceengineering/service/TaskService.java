package com.example.advanceengineering.service;

import com.example.advanceengineering.ReqRes.GetTasksResponse;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.entity.Role;
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

    /**
     * Получить задачу по проекту
     * @param project сущность проекта
     * @return список задач проекта
     */
    public List<Task> getTasksByProject(Project project) {
        return taskRepo.findByProject(project);
    }

    /**
     * Получить задачу
     * @param id идентификатор задачи
     * @return сущность задачи
     */

    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }

    /**
     * Получить список задач с нужными полями
     * @param tasks список задач
     * @param getTasksResponses  список задач с нужными полями
     * @return список задач с нужными полями
     */
    public List<GetTasksResponse> sortTasks(List<Task> tasks, List<GetTasksResponse> getTasksResponses) {
        tasks.forEach(task -> getTasksResponses.add(new GetTasksResponse(task.getName(),
                task.getTitle(), LocalDate.now(), LocalDate.now(),
                task.getStatus(), task.getType(),
                task.getUser().getUsername())));
        return getTasksResponses;
    }

    /**
     * Получить все задачи
     * @return список всех задач
     */
    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    /**
     * Обновление задачи админом
     * @param task сущность задачи
     * @param name имя задачи
     * @param title описание задачи
     * @param status статус задачи
     * @param type тип задачи
     * @return обновленная задача
     */
    public Task adminUpdateTask(Task task, String name, String title,
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

    /**
     * Обновление задачи пользователем
     * @param task сущность задачи
     * @param status статус задачи
     * @return обновленная задача
     */
    public Task userUpdateTask(Task task, Task.Status status) {

        if (status == Task.Status.Done || status == Task.Status.Progress) {
            task.setStatus(status);
            task.setStatusChangeTime(LocalDate.now());
        }

        return taskRepo.save(task);

    }

    /**
     * Создание проекта
     * @param name имя
     * @param title описание
     * @param createTime дата создания
     * @param statusChangeTime дата последнего изменения статуса
     * @param user автор з
     * @param status статус
     * @param type тип
     * @param project родительский проект
     * @return сущность задачи
     */
    public Task createTask(String name, String title, LocalDate createTime,
                           LocalDate statusChangeTime, User user, Task.Status status,
                           Task.Type type, Project project) {
        Task task = new Task(name, title, createTime, statusChangeTime, user,
                status, type, project);
        return taskRepo.save(task);
    }

    /**
     * Проверка на авторство задачи
     * @param id идентификатор задачи
     * @param user пользователь
     * @return пользователь автор задачи или нет
     */
    public boolean canDelete(Long id, User user) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isPresent()) {
            if (user.getId().equals(task.get().getUser().getId()) || user.getRoles().contains(Role.ADMIN)) {
                taskRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }
}

