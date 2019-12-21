package com.sda10.finalproject.projectmanagement.controller;

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



}
