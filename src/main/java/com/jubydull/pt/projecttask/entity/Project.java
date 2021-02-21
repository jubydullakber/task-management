package com.jubydull.pt.projecttask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "PROJECT")
@Data
@SuperBuilder
@NoArgsConstructor
public class Project extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "VERSION")
    @Version
    private long version;

    @Column(name = "PROJECT_STATUS")
    private long projectStatus;
}
