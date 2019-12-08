package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
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
            ResponseEntity<String> response = this.restTemplate
                    .postForEntity(url(RelativePath), null, String.class);
            Assertions.assertEquals("MyProject",response.getBody());
    }


}
