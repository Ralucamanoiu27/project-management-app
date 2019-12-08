package com.sda10.finalproject.projectmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;

@RestController
public class ProjectsController {

    @PostMapping("/projects")
    public String createProject(){
        return "MyProject";
    }
}
