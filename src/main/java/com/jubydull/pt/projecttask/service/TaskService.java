package com.jubydull.pt.projecttask.service;

import com.jubydull.pt.projecttask.entity.Task;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.TaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    BaseResponse createTask(TaskRequest taskRequest);

    BaseResponse editTask(long id, TaskRequest taskRequest);

    Task getTaskByTaskName(String taskName);

    Optional<Task> getTaskById(long id);


    List<Task> getAllTaskByProject(String projectName);

    List<Task> getAllExpiredTask();
    List<Task> getAllTask();

    List<Task> getAllTaskByStatus(String status);

}
