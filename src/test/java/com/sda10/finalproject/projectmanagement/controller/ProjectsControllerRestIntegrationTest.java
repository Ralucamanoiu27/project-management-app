package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class ProjectsControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void WhenAPostRequestIsReceived_thenAprojectIsCreated() {
            String RelativePath="/projects";

            ProjectDto expectedProject=ProjectDto.projectDto()
                    .setId(123L)
                    .setName("proiect1")
                    .setDescription("aaaa")
                    .setAdministrator("admin1");

            ResponseEntity<ProjectDto> response = this.restTemplate
                    .postForEntity(url(RelativePath), null,ProjectDto.class);
            Assertions.assertEquals(expectedProject,response.getBody());
    }


}
