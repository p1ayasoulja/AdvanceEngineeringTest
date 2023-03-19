package com.example.advanceengineering.service;

import com.example.advanceengineering.ReqRes.GetProjectNames;
import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.repos.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    /**
     * Создать проект
     *
     * @param name имя проекта
     * @return сущность проекта
     */
    public Project create(String name) {
        Project project = new Project(name);
        return projectRepo.save(project);
    }

    /**
     * Получить список проектов
     *
     * @return список проектов
     */

    public List<Project> getProjects() {
        return projectRepo.findAll();
    }

    /**
     * Удалить проект
     *
     * @param id идентификатор проекта
     */
    public void delete(Long id) {
        List<Project> projects = projectRepo.findAll();
        projects.forEach(project -> {
            if (project.getParent() != null) {
                if (project.getParent().getId() == (id)) {
                    project.setParent(null);
                    projectRepo.save(project);
                }
            }
        });
        projectRepo.deleteById(id);

    }

    /**
     * Обновить проект
     *
     * @param id        идентификатор проекта
     * @param name      новое имя проекта
     * @param parent_id новый идентификатор родительского проекта
     * @return обновленный проект
     */
    public Project updateProject(Long id, String name, Long parent_id) {
        Project project = projectRepo.findById(id).get();
        project.setName(name);
        if (parent_id != null && !parent_id.equals(id)) {
            project.setParent(projectRepo.findById(parent_id).get());
        }
        return projectRepo.save(project);
    }

    /**
     * Получить проект
     *
     * @param id идентификатор проекта
     * @return сущность проекта
     */
    public Optional<Project> getProject(Long id) {
        if (projectRepo.existsById(id)) {
            return projectRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Имеет ли проект родителя
     *
     * @param project сущность проекта
     * @return имеет ли проект родителя
     */
    public boolean hasParent(Project project) {
        return project.getParent() != null;
    }

    /**
     * Получить список подпроектов
     *
     * @param project сущность проекта
     * @return список подпроектов проекта
     */
    public List<Project> subProjects(Project project) {
        List<Project> projects = projectRepo.findAll();
        List<Project> subProjectlist = new ArrayList<>();
        projects.forEach(project1 -> {
            if (project1.getParent() != null) {
                if (project.getId().equals(project1.getParent().getId())) {
                    subProjectlist.add(project1);
                }
            }
        });
        return subProjectlist;
    }

    /**
     * Получить список проектов с нужными полями
     *
     * @param projects       список проектов с одними полями для показа
     * @param getSubProjects список проектов с нужными полями для показа
     * @return список проектов с нужными полями для показа
     */
    public List<GetProjectNames> refactorProjects(List<Project> projects, List<GetProjectNames> getSubProjects) {
        projects.forEach(project -> getSubProjects.add(new GetProjectNames(project.getName())));
        return getSubProjects;
    }

}
