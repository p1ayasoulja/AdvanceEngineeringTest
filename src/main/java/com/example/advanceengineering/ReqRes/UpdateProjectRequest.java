package com.example.advanceengineering.ReqRes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("Запрос на обновление проекта")
public class UpdateProjectRequest {
    @ApiModelProperty("Имя проекта")
    private final String name;
    @ApiModelProperty("Идентификатор родительского проекта")
    private final Long parent_id;

    @JsonCreator
    public UpdateProjectRequest(@JsonProperty("name") String name, @JsonProperty("parent") Long id) {
        this.name = name;
        this.parent_id = id;
    }

    public String getName() {
        return name;
    }

    public Long getParent_id() {
        return parent_id;
    }

}
