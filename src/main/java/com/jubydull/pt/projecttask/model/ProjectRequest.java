package com.jubydull.pt.projecttask.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectRequest {
    @NotNull(message = "Please Provide a Project name")
    @NotBlank(message = "Please Provide a Project name")
    private String name;

    private String userName;
}
