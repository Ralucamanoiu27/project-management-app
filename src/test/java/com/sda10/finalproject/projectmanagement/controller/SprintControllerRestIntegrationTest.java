package com.sda10.finalproject.projectmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.dto.ProjectMapper;
import com.sda10.finalproject.projectmanagement.dto.SprintDto;
import com.sda10.finalproject.projectmanagement.dto.UserMapper;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.Role;
import com.sda10.finalproject.projectmanagement.model.Sprint;
import com.sda10.finalproject.projectmanagement.model.User;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import com.sda10.finalproject.projectmanagement.repository.SprintRepository;
import com.sda10.finalproject.projectmanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static com.sda10.finalproject.projectmanagement.controller.SprintController.API_SPRINT;

public class SprintControllerRestIntegrationTest extends RestIntegrationTest {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    // cand data from < date to -->ok si nu se suprapun cu alte sprinturi
    // cand data from > date to --> not ok
    // cand data from < date to --> ok dar se suprapune cu alt sprint

//------------------------------------------------------------------------------------------------------------
    // cand data from < date to -->ok si nu se suprapun cu alte sprinturi

    @Test
    public void givenSprintDateFromIsSmallerThanDateToAndIsNotOverLapping_whenCreateSprint_ThenTestOk() {
        User user = new User();
        user.setUserName("gica")
                .setRole(Role.ADMIN)
                .setPassword("6777");
        user = userRepository.save(user);

        Project project = new Project();
        project.setDescription("proiect7 este blabla")
                .setName("proiect7")
                .setAdministrator(user);

        project = repository.save(project);

        Sprint sprint = new Sprint();

        sprint.setDateFrom(LocalDate.of(2019, 11, 23))
                .setDateTo(LocalDate.of(2019, 12, 3))
                .setProject(project);

        sprintRepository.save(sprint);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,4))
                .setDateTo(LocalDate.of(2019,12,20))
                .setProjectDto(projectMapper.toDto(project));

        try {
            String s = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(newSprintDetails);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//
//        String relativePath = API_SPRINT;
//        ResponseEntity<SprintDto> response = this.restTemplate.postForEntity(url(relativePath), newSprintDetails, SprintDto.class);
//
//        newSprintDetails.setId(response.getBody().id);
//        //then
//        Assertions.assertEquals(newSprintDetails, response.getBody());
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    // cand data from > date to --> not ok
    @Test
    public void givenSprintDateFromIsBiggerThanDateTo_whenCreateSprint_ThenTestIsNotOk() {
        User user = new User();
        user.setUserName("gica")
                .setRole(Role.ADMIN)
                .setPassword("6777");
        user = userRepository.save(user);

        Project project = new Project();
        project.setDescription("proiect7 este blabla")
                .setName("proiect7")
                .setAdministrator(user);

        project = repository.save(project);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,20))
                .setDateTo(LocalDate.of(2019,12,4))
                .setProjectDto(projectMapper.toDto(project));

        String relativePath = API_SPRINT;
        ResponseEntity<SprintDto> response = this.restTemplate.postForEntity(url(relativePath), newSprintDetails, SprintDto.class);

        //then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    ///  cand data from < date to -->ok si nu se suprapun cu alte sprinturi
    @Test
    public void givenSprintDateFromIsSmallerThanDateTo_whenCreateSprint_ThenTestIsNotOk() {
        User user = new User();
        user.setUserName("gica")
                .setRole(Role.ADMIN)
                .setPassword("6777");
        user = userRepository.save(user);

        Project project = new Project();
        project.setDescription("proiect7 este blabla")
                .setName("proiect7")
                .setAdministrator(user);

        project = repository.save(project);

        Sprint sprint = new Sprint();

        sprint.setDateFrom(LocalDate.of(2019, 11, 30))
                .setDateTo(LocalDate.of(2019, 12, 10))
                .setProject(project);

        sprint = sprintRepository.save(sprint);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,4))
                .setDateTo(LocalDate.of(2019,12,20))
                .setProjectDto(projectMapper.toDto(project));

        String relativePath = API_SPRINT;
        ResponseEntity<SprintDto> response = this.restTemplate.postForEntity(url(relativePath), newSprintDetails, SprintDto.class);

        //then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    //de verificat restul in afara de create
    // creat de Cristina si neverificat
    @Test
    public void givenExistingId_whenGetSprintById_ThenReturnSprint() {
        User user = new User();
        user.setUserName("gica")
                .setRole(Role.ADMIN)
                .setPassword("6777");
        user = userRepository.save(user);

        Project project = new Project();
        project.setDescription("proiect8 este unudoitrei")
                .setName("proiect8")
                .setAdministrator(user);

        project = repository.save(project);

        Sprint sprint = new Sprint();

        sprint.setDateFrom(LocalDate.of(2019, 11, 23))
                .setDateTo(LocalDate.of(2019, 12, 3))
                .setProject(project);

        sprint = sprintRepository.save(sprint);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,4))
                .setDateTo(LocalDate.of(2019,12,20))
                .setProjectDto(projectMapper.toDto(project));

        String relativePath = API_SPRINT;
        ResponseEntity<SprintDto> response = this.restTemplate.postForEntity(url(relativePath), newSprintDetails, SprintDto.class);

        newSprintDetails.setId(response.getBody().id);
        //then
        Assertions.assertEquals(newSprintDetails, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void givenNonExistingId_whenGetSprintByID_ThenStatusCodeIsInternalServerError() {
        long nonExistingId = 1L;

        String relativePath = API_SPRINT + "/" + nonExistingId;
        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), SprintDto.class)
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }
    @Test
    public void givenSprint_whenDeleteRequestIsReceived_ThenSprintDeleted() {
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

        Sprint sprint = new Sprint();

        sprint.setDateFrom(LocalDate.of(2019, 11, 23))
                .setDateTo(LocalDate.of(2019, 12, 3))
                .setProject(existingProject);

        sprint = sprintRepository.save(sprint);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,4))
                .setDateTo(LocalDate.of(2019,12,20))
                .setProjectDto(projectMapper.toDto(existingProject));


        String relativePath = API_SPRINT + "/" + existingProject.getId();
        this.restTemplate.delete(relativePath, existingProject.getId());

        Optional<Project> updateProject = this.repository.findById(existingProject.getId());

        Assertions.assertFalse(updateProject.isPresent());
    }

    @Test
    public void givenSprint_whenPutRequestIsReceived_ThenUpdateNewSprint() {
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


        Sprint sprint = new Sprint();

        sprint.setDateFrom(LocalDate.of(2019, 11, 23))
                .setDateTo(LocalDate.of(2019, 12, 3))
                .setProject(existingProject);

        sprint = sprintRepository.save(sprint);

        SprintDto newSprintDetails = SprintDto.sprintDto()
                .setDateFrom(LocalDate.of(2019,12,4))
                .setDateTo(LocalDate.of(2019,12,20))
                .setProjectDto(projectMapper.toDto(existingProject));

        String relativePath = API_SPRINT + "/" + newProject.id;
        this.restTemplate.put(relativePath, newProject);

        Optional<Project> updateProject = this.repository.findById(newProject.id);

        Assertions.assertTrue(updateProject.isPresent());
        Assertions.assertEquals(newProject, projectMapper.toDto(updateProject.get()));

    }
}
