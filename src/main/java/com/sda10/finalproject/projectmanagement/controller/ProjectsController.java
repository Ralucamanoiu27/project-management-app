package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.dto.ProjectMapper;
import com.sda10.finalproject.projectmanagement.exception.NotFoundException;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import com.sda10.finalproject.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.sda10.finalproject.projectmanagement.controller.ProjectsController.API_PROJECTS;


@RestController
@RequestMapping(API_PROJECTS)
public class ProjectsController {



    public static final String API_PROJECTS="/api/projects";
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMapper projectMapper;



    @PostMapping()
    public ProjectDto createProject(@RequestBody ProjectDto projectDetails) {

        Project project = projectMapper.toEntity(projectDetails);

        project = projectService.createProject(project);

        return projectMapper.toDto(project);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
                .orElseThrow(NotFoundException::new);

        ProjectDto response = projectMapper.toDto(project);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDetails) {
        Project project = projectMapper.toEntity(projectDetails);

        projectService.updateProject(id, project);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<ProjectDto> searchAll( ) {
    return projectService.findAll()
       .stream()
       .map(projectMapper::toDto)
               .collect(Collectors.toList());
  }

//    @GetMapping
//    public ResponseEntity<ProjectDto> searchProjectByNameProject(@RequestParam(required = false) String name) {
//        Project project =projectService.searchProjectByNameProject(name).orElseThrow(NotFoundException::new);
//        ProjectDto response =projectMapper.toDto(project);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//
//    }

//
//    @GetMapping("/search")
//    public List<ProjectDto> searchProjectByName(@RequestParam(required = false) String name) {
//        return projectService.searchByProjectName(name)
//                .stream()
//                .map(projectMapper::toDto)
//                .collect(Collectors.toList());
//    }

}
