package com.jubydull.pt.projecttask.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class TaskResponse extends BaseResponse {

    private String description;
    private String taskStatus;
    private String projectName;
    private LocalDate dueDate;

}
