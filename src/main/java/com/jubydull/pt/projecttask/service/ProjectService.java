package com.jubydull.pt.projecttask.service;

import com.jubydull.pt.projecttask.entity.Project;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.ProjectRequest;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();
    BaseResponse createProject(ProjectRequest projectRequest);
    BaseResponse deleteProject(String name , String userName);
    List<Project> getAllProjectsByUser(String user);
}
