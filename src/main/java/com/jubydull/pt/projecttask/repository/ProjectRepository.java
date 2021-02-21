package com.jubydull.pt.projecttask.repository;

import com.jubydull.pt.projecttask.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findProjectById(long id);
    Project findProjectByProjectName(String name);
    Project findProjectByCreatedBy(String createdBy);
}
