package com.example.advanceengineering.ReqRes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api("Получить задачи и под проекты проекта")
public class GetProjectInfoResponse {
    @ApiModelProperty("Имя проекта")
    private String name;
    @ApiModelProperty("Список задач")
    private List<GetTasksResponse> taskList;
    @ApiModelProperty("Список подпроектов")
    private List<GetProjectNames> subprojects;

    public GetProjectInfoResponse(String name, List<GetTasksResponse> taskList, List<GetProjectNames> subprojects) {
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

    public List<GetTasksResponse> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<GetTasksResponse> taskList) {
        this.taskList = taskList;
    }

    public List<GetProjectNames> getSubprojects() {
        return subprojects;
    }

    public void setSubprojects(List<GetProjectNames> subprojects) {
        this.subprojects = subprojects;
    }
}
