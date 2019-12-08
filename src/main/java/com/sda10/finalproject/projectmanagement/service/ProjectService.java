package com.sda10.finalproject.projectmanagement.service;

import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }
   // public <Project> getProjectById(long id){ return; projectRepository.findAllById(id);}
}
