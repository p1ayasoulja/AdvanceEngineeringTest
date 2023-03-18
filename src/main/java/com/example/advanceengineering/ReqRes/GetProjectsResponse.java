package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api("Вывод списка проектов")
public class GetProjectsResponse {
    @ApiModelProperty("Список проектов")
    private final List<GetProjectResponse> projectList;

    public GetProjectsResponse(List<GetProjectResponse> projectList) {
        this.projectList = projectList;
    }

    @JsonProperty("projectList")
    public List<GetProjectResponse> getProjectsResponse() {
        return projectList;
    }

    public List<GetProjectResponse> getProjectList() {
        return projectList;
    }
}
