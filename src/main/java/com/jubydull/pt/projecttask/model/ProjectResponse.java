package com.jubydull.pt.projecttask.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectResponse extends BaseResponse {
    private String name;
}
