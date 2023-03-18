package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class GetProjectNames {
    @ApiModelProperty("Имя проекта")
    private String name;
    public GetProjectNames(@JsonProperty("Project name") String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }

}
