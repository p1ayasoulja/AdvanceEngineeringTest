package com.example.advanceengineering.service;

import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.repos.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Project create(String name) {
        Project project = new Project(name);
        return projectRepo.save(project);
    }

    public List<Project> getProjects() {
        return projectRepo.findAll();
    }

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

    public Project updateProject(Long id, String name, Long parent_id) {
        Project project = projectRepo.findById(id).get();
        project.setName(name);
        if (parent_id != null && !parent_id.equals(id)) {
            project.setParent(projectRepo.findById(parent_id).get());
        }
        return projectRepo.save(project);
    }

    public Optional<Project> getProject(Long id) {
        if (projectRepo.existsById(id)) {
            return projectRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }
}
