package com.jubydull.pt.projecttask.service;

import com.jubydull.pt.projecttask.entity.Project;
import com.jubydull.pt.projecttask.model.BaseResponse;
import com.jubydull.pt.projecttask.model.ProjectRequest;
import com.jubydull.pt.projecttask.model.ProjectResponse;
import com.jubydull.pt.projecttask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public BaseResponse createProject(ProjectRequest projectRequest) {
        BaseResponse response = new ProjectResponse();
        Project projectExist = projectRepository.findProjectByProjectName(projectRequest.getName());
        if (projectExist == null) {
            Project pro = Project.builder().projectName(projectRequest.getName())
                    .createdBy(projectRequest.getUserName())
                    .createdDate(LocalDate.now())
                    .projectStatus(0).build();
            projectRepository.save(pro);
            response.setSuccessMsg("Project : " + pro.getProjectName() + " Created Successfully");
            return response;
        }
        response.setErrorMsg("Project : " + projectRequest.getName() + "  Already Exist");
        return response;

    }

    @Override
    public BaseResponse deleteProject(String name, String userName) {
        BaseResponse response = new ProjectResponse();
        Project projectDb = projectRepository.findProjectByProjectName(name);
        if (projectDb == null) {
            response.setErrorMsg("Project Not Found");
        } else {
            projectDb.setProjectStatus(1);
            projectDb.setLastModifiedDate(LocalDate.now());
            projectDb.setLastModifiedBy(userName);
        }
        response.setSuccessMsg("Project : " + projectDb.getProjectName() + "  Deleted");
        return response;
    }

    @Override
    public List<Project> getAllProjectsByUser(String user) {
        return (List<Project>) projectRepository.findProjectByCreatedBy(user);
    }
}
