package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "Ответ на создание задачи")
public class CreateProjectResponse {
    @ApiModelProperty(value = "Имя проекта", required = true)
    private String name;

    public CreateProjectResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}
