package com.example.advanceengineering.repos;

import com.example.advanceengineering.entity.Project;
import com.example.advanceengineering.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
}
