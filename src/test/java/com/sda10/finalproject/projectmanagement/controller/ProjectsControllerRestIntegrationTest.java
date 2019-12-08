package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class ProjectsControllerRestIntegrationTest extends RestIntegrationTest {

        @Autowired
        private ProjectRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void WhenAPostRequestIsReceived_thenAprojectIsCreated() {
            String RelativePath="/projects";

            ProjectDto expectedProject=ProjectDto.projectDto()
                    .setName("proiect1")
                    .setDescription("aaaa")
                    .setAdministrator("admin1");

            ResponseEntity<ProjectDto> response = this.restTemplate
                    .postForEntity(url(RelativePath), expectedProject,ProjectDto.class);
            Assertions.assertEquals(expectedProject.setId(response.getBody().id),response.getBody());
    }
@Test
    public void givenExistingId_whenGetPorjectById_thenReturnProject() {

    Project expectedProject=new Project();
    expectedProject.setName("project3");
    expectedProject=repository.saveAndFlush(expectedProject);

    ProjectDto expectedResult=ProjectDto
            .projectDto()
            .setId(expectedProject.getId())
            .setName(expectedProject.getName())
            .setDescription(expectedProject.getDescripton())
            .setAdministrator(expectedProject.getAdministrator());

    String relativePath="/projects/" + expectedProject.getId();
    ResponseEntity<ProjectDto> response=this.restTemplate.getForEntity(relativePath,ProjectDto.class);

    Assertions.assertEquals(expectedResult, response.getBody());
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
}


}
