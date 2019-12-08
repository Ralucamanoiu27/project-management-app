package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProjectsController {

    @PostMapping("/projects")
    public ProjectDto createProject(){
        return ProjectDto.projectDto()
                .setId(123L)
                .setName("proiect1")
                .setDescription("aaaa")
                .setAdministrator("admin1");
    }
}
