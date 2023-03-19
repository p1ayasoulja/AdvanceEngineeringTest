package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Ответ на получение имен проектов")
public class GetProjectNames {
    @ApiModelProperty("Имя проекта")
    private String name;

    @JsonCreator
    public GetProjectNames(@JsonProperty("Project name") String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
