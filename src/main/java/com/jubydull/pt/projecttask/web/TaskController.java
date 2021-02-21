package com.jubydull.pt.projecttask.web;

import com.jubydull.pt.projecttask.entity.Task;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.TaskRequest;
import com.jubydull.pt.projecttask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Task> findAllTask() {
        return taskService.getAllTask();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public BaseResponse createTask(@RequestBody TaskRequest taskRequest) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskRequest.setUserName(userName);
        return taskService.createTask(taskRequest);

    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public BaseResponse updateTask(@PathVariable("id") long id, @RequestBody TaskRequest taskRequest) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskRequest.setUserName(userName);
        return taskService.editTask(id, taskRequest);
    }

    @GetMapping("/get-task")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Task findTaskById(@PathVariable("id") long id) {
        return taskService.getTaskById(id).
                orElseThrow(() -> new IllegalArgumentException("No task found by ID : " + id));
    }

    @GetMapping("/get-expire-task")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Task> findExpireTask() {
        return taskService.getAllExpiredTask();
    }

    @GetMapping("/get-task-by-status/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Task> findAllTaskByStatus(@PathVariable("status") String status) {
        return taskService.getAllTaskByStatus(status);
    }

    @GetMapping("/get-task-by-project/{projectName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Task> findAllTaskByProject(@PathVariable("projectName") String projectName) {
        return taskService.getAllTaskByProject(projectName);
    }


}
