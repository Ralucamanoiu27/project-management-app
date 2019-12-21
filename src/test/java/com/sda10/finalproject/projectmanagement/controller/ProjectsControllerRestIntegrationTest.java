package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.dto.ProjectMapper;
import com.sda10.finalproject.projectmanagement.dto.UserMapper;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.Role;
import com.sda10.finalproject.projectmanagement.model.User;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import com.sda10.finalproject.projectmanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.sda10.finalproject.projectmanagement.controller.ProjectsController.API_PROJECTS;

public class ProjectsControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void givenProjectDetails_whenPostRequestIsReceived_ThenCreateNewProjectDetails() {

        User user = new User();
        user.setUserName("raluca")
                    .setPassword("123")
                    .setRole(Role.ADMIN);
        user = userRepository.save(user);

        ProjectDto expectedResult = ProjectDto.projectDto()
                .setName("proiect4")
                .setDescription("bbbb")
                .setAdministrator(userMapper.toDto(user));
        String relativePath = API_PROJECTS;
        ResponseEntity<ProjectDto> response = this.restTemplate.postForEntity(url(relativePath), expectedResult, ProjectDto.class);

        expectedResult.setId(response.getBody().id);
        //then
        Assertions.assertEquals(expectedResult, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void givenExistingId_whenGetProjectById_ThenReturnProject() {
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);

        Project expectedProject = new Project();

        expectedProject.setName("project3")
                .setDescription("aaaa")
                .setAdministrator(user);

        expectedProject = repository.save(expectedProject);

        ResponseEntity<ProjectDto> response = this.restTemplate
                .getForEntity(API_PROJECTS + "/" + expectedProject.getId(), ProjectDto.class);

        ProjectDto newProjectDto = response.getBody();
        Optional<Project> newProject = this.repository.findById(newProjectDto.id);
        Assertions.assertTrue(newProject.isPresent());
        Assertions.assertEquals(newProjectDto.setId(newProjectDto.id), newProjectDto);
    }

    @Test
    public void givenProjectDetails_whenPutRequestIsReceived_ThenUpdateNewProjectDetails() {
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);

        Project existingProject = new Project();

        existingProject.setName("project3")
                .setDescription("aaaa")
                .setAdministrator(user);

        existingProject = repository.save(existingProject);

        ProjectDto newProject = projectMapper.toDto(existingProject)
                .setName("project4")
                .setDescription("bbbb");

        String relativePath = API_PROJECTS + "/" + newProject.id;
        this.restTemplate.put(relativePath, newProject);

        Optional<Project> updateProject = this.repository.findById(newProject.id);

        Assertions.assertTrue(updateProject.isPresent());
        Assertions.assertEquals(newProject, projectMapper.toDto(updateProject.get()));

    }

    @Test
    public void givenNonExistingId_whenGetProjectsByID_ThenStatusCodeIsInternalServerError() {
        long nonExistingId = 1L;

        String relativePath = API_PROJECTS + "/" + nonExistingId;
        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), ProjectDto.class)
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }

    @Test
    public void givenProjectDetails_whenDeleteRequestIsReceived_ThenProjectIsDeleted() {
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);

        Project existingProject = new Project();

        existingProject.setName("project3")
                .setDescription("aaaa")
                .setAdministrator(user);

        existingProject = repository.save(existingProject);


        String relativePath = API_PROJECTS + "/" + existingProject.getId();
        this.restTemplate.delete(relativePath, existingProject.getId());

        Optional<Project> updateProject = this.repository.findById(existingProject.getId());

        Assertions.assertFalse(updateProject.isPresent());
    }
}
