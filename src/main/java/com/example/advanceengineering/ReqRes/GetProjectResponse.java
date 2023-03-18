package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Получить проект")
public class GetProjectResponse {
    @ApiModelProperty("Имя проекта")
    private final String name;

    public GetProjectResponse(@JsonProperty("name") String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
