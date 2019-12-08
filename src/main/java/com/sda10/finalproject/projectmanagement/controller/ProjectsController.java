package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProjectsController {
    @Autowired
    private ProjectService projectService;


    @PostMapping("/projects")
    public ProjectDto createProject(@RequestBody ProjectDto projectDetails){
        Project project=new Project();
        project.setName(projectDetails.name)
                .setDescripton(projectDetails.description)
                .setAdministrator(projectDetails.administrator);
        project = projectService.createProject(project);

        return ProjectDto.projectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescripton())
                .setAdministrator(project.getAdministrator());
    }

//    @GetMapping


}
