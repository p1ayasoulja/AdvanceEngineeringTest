package com.example.advanceengineering.ReqRes;

import com.example.advanceengineering.entity.Task;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Запрос на создание задачи")
public class CreateTaskRequest {
    @ApiModelProperty("Имя задачи")
    private final String name;
    @ApiModelProperty("Описание задачи")
    private final String title;
    @ApiModelProperty("Статус задачи")
    private final Task.Status status;
    @ApiModelProperty("Тип задачи")
    private final Task.Type type;
    @ApiModelProperty("Идентификатор проекта")
    private final Long project_id;

    @JsonCreator
    public CreateTaskRequest(@JsonProperty("name") String name, @JsonProperty("title") String title,
                             @JsonProperty("status") Task.Status status, @JsonProperty("type") Task.Type type,
                             @JsonProperty("project id") Long project_id) {
        this.name = name;
        this.title = title;
        this.status = status;
        this.type = type;
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Task.Status getStatus() {
        return status;
    }

    public Task.Type getType() {
        return type;
    }

    public Long getProject_id() {
        return project_id;
    }
}
