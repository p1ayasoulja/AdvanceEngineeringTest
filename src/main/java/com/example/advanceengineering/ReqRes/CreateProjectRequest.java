package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Запрос на создание задачи")
public class CreateProjectRequest {
    @ApiModelProperty("Имя проекта")
    private final String name;

    @JsonCreator
    public CreateProjectRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
