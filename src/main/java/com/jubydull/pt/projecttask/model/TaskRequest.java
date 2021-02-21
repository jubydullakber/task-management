package com.jubydull.pt.projecttask.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotNull(message = "Please Provide Task Description")
    @NotBlank(message = "Please Provide Task Description")
    private String description;
    private String status;
    @NotNull(message = "Please Select Project")
    @NotBlank(message = "Please Provide Project Name")
    private String projectName;

    @NotNull(message = "Please Select Valid Date")
    @NotBlank(message = "Please Select Valid Date")
    @Past(message = "Stale Date Not Allowed")
    private LocalDate dueDate;

    private String userName;

}
