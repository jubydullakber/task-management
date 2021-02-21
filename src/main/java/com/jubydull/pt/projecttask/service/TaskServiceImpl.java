package com.jubydull.pt.projecttask.service;

import com.jubydull.pt.projecttask.entity.Project;
import com.jubydull.pt.projecttask.entity.Task;
import com.jubydull.pt.projecttask.enums.Status;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.TaskRequest;
import com.jubydull.pt.projecttask.model.TaskResponse;
import com.jubydull.pt.projecttask.repository.ProjectRepository;
import com.jubydull.pt.projecttask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public BaseResponse createTask(TaskRequest taskRequest) {
        BaseResponse response = new TaskResponse();
        Project project = projectRepository.findProjectByProjectName(taskRequest.getProjectName());
        if (project == null) {
            response.setErrorMsg("No Project found as :" + taskRequest.getProjectName());
            return response;
        }
        response.setStatus(200);
        Task taskExist = taskRepository.findAllTaskByDescription(taskRequest.getDescription());
        if (taskExist != null) {
            response.setErrorMsg("Task : " + taskRequest.getDescription() + " Already Exist");
        } else {
            Task task = Task.builder().taskName(taskRequest.getDescription()).createdBy(taskRequest.getUserName()).createdDate(LocalDate.now()).
                    dueDate(taskRequest.getDueDate()).
                    projectName(taskRequest.getProjectName()).build();

            taskRepository.save(task);

            response.setSuccessMsg("Task : " + task.getTaskName() + " Create Successfully");
        }
        return response;
    }

    @Override
    public BaseResponse editTask(long id, TaskRequest taskRequest) {
        BaseResponse response = new TaskResponse();
        response.setStatus(200);

        Task taskNameExist = taskRepository.findAllTaskByDescription(taskRequest.getDescription());
        if (taskNameExist != null) {
            response.setErrorMsg("Task Description Already Exist With Other Task :  " + taskRequest.getDescription());
            return response;
        }

        Optional<Task> taskExist = taskRepository.findById(id);

        Task taskEdit = taskExist.get();
        if (taskEdit == null) {
            response.setErrorMsg("Task Description Not Found :  " + taskRequest.getDescription());
            return response;
        }
        taskEdit.setStatus(Status.valueOf(taskRequest.getStatus()).getVal());
        taskEdit.setDescription(taskRequest.getDescription());
        taskEdit.setDueDate(taskRequest.getDueDate());
        taskEdit.setLastModifiedBy(taskRequest.getUserName());
        taskEdit.setLastModifiedDate(LocalDate.now());
        taskRepository.save(taskEdit);
        return response;
    }

    @Override
    public Task getTaskByTaskName(String taskName) {
        return taskRepository.findAllTaskByDescription(taskName);
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTaskByProject(String projectName) {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> getAllExpiredTask() {
        return taskRepository.findAllTaskByStatus(Status.CLOSED.getVal());
    }

    @Override
    public List<Task> getAllTask() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTaskByStatus(String status) {
        return taskRepository.findAllTaskByStatus(Status.valueOf(status).getVal());
    }
}
