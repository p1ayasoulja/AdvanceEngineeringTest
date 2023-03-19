package com.example.advanceengineering.ReqRes;

import com.example.advanceengineering.entity.Task;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@Api("Показать список задач")
public class GetTasksResponse {
    @ApiModelProperty("Имя задачи")
    private String name;
    @ApiModelProperty("Описание задачи")
    private String title;
    @ApiModelProperty("Дата создания задачи")
    private LocalDate createDate;
    @ApiModelProperty("Дата изменения статуса задачи")
    private LocalDate StatusChangeTime;
    @ApiModelProperty("Статус задачи")
    private Task.Status status;
    @ApiModelProperty("Тип задачи")
    private Task.Type type;
    @ApiModelProperty("Никнейм автора задачи")
    private String username;

    public GetTasksResponse(String name, String title, LocalDate createDate,
                            LocalDate statusChangeTime, Task.Status status,
                            Task.Type type, String username) {
        this.name = name;
        this.title = title;
        this.createDate = createDate;
        this.StatusChangeTime = statusChangeTime;
        this.status = status;
        this.type = type;
        this.username = username;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getStatusChangeTime() {
        return StatusChangeTime;
    }

    public void setStatusChangeTime(LocalDate statusChangeTime) {
        StatusChangeTime = statusChangeTime;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }

    public Task.Type getType() {
        return type;
    }

    public void setType(Task.Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
