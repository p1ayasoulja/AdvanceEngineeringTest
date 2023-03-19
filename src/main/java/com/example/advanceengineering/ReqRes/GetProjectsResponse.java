package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api("Ответ на получение проектов")
public class GetProjectsResponse {
    @ApiModelProperty("Имя проекта")
    private String name;
    @ApiModelProperty("Задачи проекта")
    private List<GetTasksResponse> taskList;


    public GetProjectsResponse(@JsonProperty("name") String name,
                               @JsonProperty("project tasks") List<GetTasksResponse> taskList) {
        this.name = name;
        this.taskList = taskList;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("taskList")

    public List<GetTasksResponse> getTaskList() {
        return taskList;
    }


}
