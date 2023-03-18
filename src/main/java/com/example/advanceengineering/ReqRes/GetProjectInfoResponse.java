package com.example.advanceengineering.ReqRes;

import com.example.advanceengineering.entity.Task;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api("Получить задачи и под проекты проекта")
public class GetProjectInfoResponse {
    @ApiModelProperty("Имя проекта")
    private String name;
    @ApiModelProperty("Список задач")
    private List<Task> taskList;
    @ApiModelProperty("Список подпроектов")
    private List<GetProjectsResponse> subprojects;

    public GetProjectInfoResponse(String name, List<Task> taskList, List<GetProjectsResponse> subprojects) {
        this.name = name;
        this.taskList = taskList;
        this.subprojects = subprojects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<GetProjectsResponse> getSubprojects() {
        return subprojects;
    }

    public void setSubprojects(List<GetProjectsResponse> subprojects) {
        this.subprojects = subprojects;
    }
}
