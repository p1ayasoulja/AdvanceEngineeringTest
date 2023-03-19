package com.example.advanceengineering.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@jakarta.persistence.Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;
    @Column(name = "createTime")
    private LocalDate createTime;
    @Column(name = "StatusChangeTime")
    private LocalDate StatusChangeTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "status")
    private Status status;
    @Column(name = "type")
    private Type type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {
    }

    public Task(String name, String title, LocalDate createTime, LocalDate statusChangeTime, User user, Status status, Type type, Project project) {
        this.name = name;
        this.title = title;
        this.createTime = createTime;
        StatusChangeTime = statusChangeTime;
        this.user = user;
        this.status = status;
        this.type = type;
        this.project = project;
    }

    public Task(String name, String title, LocalDate createTime, LocalDate statusChangeTime, Status status, Type type, Project project) {
        this.name = name;
        this.title = title;
        this.createTime = createTime;
        StatusChangeTime = statusChangeTime;
        this.status = status;
        this.type = type;
        this.project = project;
    }

    public Task(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getStatusChangeTime() {
        return StatusChangeTime;
    }

    public void setStatusChangeTime(LocalDate statusChangeTime) {
        StatusChangeTime = statusChangeTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public enum Status {
        New, Progress, Done
    }

    public enum Type {
        Manager, Specialist
    }
}
