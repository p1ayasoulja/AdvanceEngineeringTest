package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Ответ на обновление проекта")
public class UpdateProjectResponse {
    @ApiModelProperty("Имя проекта")
    private String name;

    public UpdateProjectResponse(@JsonProperty("name") String name) {
        this.name = name;

    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

}
