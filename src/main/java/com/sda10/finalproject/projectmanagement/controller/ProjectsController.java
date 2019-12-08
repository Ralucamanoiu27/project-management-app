package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.exception.NotFoundException;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(NotFoundException::new);

        ProjectDto response = ProjectDto.projectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescripton())
                .setAdministrator(project.getAdministrator());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/projects/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDetails){
        Project project=new Project();
        project.setName(projectDetails.name)
                .setDescripton(projectDetails.description)
                .setAdministrator(projectDetails.administrator);
        projectService.updateProject(id, project);

        return new ResponseEntity<>(HttpStatus.OK);

 }

}
