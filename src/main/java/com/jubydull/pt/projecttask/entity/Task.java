package com.jubydull.pt.projecttask.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TASK")
@Data
@SuperBuilder
@NoArgsConstructor
public class Task extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTIONS")
    private String description;

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "VERSION")
    @Version
    private int version;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;


}
