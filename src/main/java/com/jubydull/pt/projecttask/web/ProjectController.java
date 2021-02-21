package com.jubydull.pt.projecttask.web;

import com.jubydull.pt.projecttask.entity.Project;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.ProjectRequest;
import com.jubydull.pt.projecttask.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public BaseResponse createProject(@RequestBody ProjectRequest projectRequest) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        projectRequest.setUserName(userName);
        return projectService.createProject(projectRequest);
    }

    @GetMapping("/all-project")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Project> getAllProject() {
        return projectService.getAllProjects();
    }

    @GetMapping("/remove-project/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public BaseResponse deleteProject(@PathVariable("name") String name) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return projectService.deleteProject(name, userName);
    }
}
