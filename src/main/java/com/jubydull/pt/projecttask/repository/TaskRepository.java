package com.jubydull.pt.projecttask.repository;

import com.jubydull.pt.projecttask.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllTaskByStatus(int status);
    Task findAllTaskByDescription(String description);

}
