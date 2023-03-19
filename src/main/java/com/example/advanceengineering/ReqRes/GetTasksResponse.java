package com.example.advanceengineering.ReqRes;

import com.example.advanceengineering.entity.Task;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@Api("Ответ на показ всего списка задач")
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
    public GetTasksResponse(@JsonProperty("name")String name,@JsonProperty("title") String title,@JsonProperty("creationDate") LocalDate createDate,
                            @JsonProperty("statusChangeTime") LocalDate statusChangeTime,@JsonProperty("status") Task.Status status,
                            @JsonProperty("type")Task.Type type,@JsonProperty("username") String username) {
        this.name = name;
        this.title = title;
        this.createDate = createDate;
        this.StatusChangeTime = statusChangeTime;
        this.status = status;
        this.type = type;
        this.username = username;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("creationDate")
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    @JsonProperty("statusChangeTime")
    public void setStatusChangeTime(LocalDate statusChangeTime) {
        StatusChangeTime = statusChangeTime;
    }
    @JsonProperty("status")
    public void setStatus(Task.Status status) {
        this.status = status;
    }
    @JsonProperty("type")
    public void setType(Task.Type type) {
        this.type = type;
    }
    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getStatusChangeTime() {
        return StatusChangeTime;
    }

    public Task.Status getStatus() {
        return status;
    }

    public Task.Type getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }
}
