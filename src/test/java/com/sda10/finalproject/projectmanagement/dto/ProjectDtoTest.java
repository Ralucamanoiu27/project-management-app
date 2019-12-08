package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest extends UnitTest {

    @Test
    public  void givenTwoEqualProjects_whenCompared_theResultIsTrue() {
        ProjectDto projectDto1=ProjectDto.projectDto()
                .setId(123L)
                .setName("proiect1")
                .setDescription("aaaa")
                .setAdministrator("admin1");

        ProjectDto projectDto2=ProjectDto.projectDto()
                .setId(123L)
                .setName("proiect1")
                .setDescription("aaaa")
                .setAdministrator("admin1");

        boolean comparisionResult=projectDto1.equals(projectDto2);
        assertTrue(comparisionResult);

    }
    @Test
    public void givenTwoProjectsWithDifferentIds_whenComparedTheResultIsFalse() {
        ProjectDto projectDto1=ProjectDto.projectDto()
                .setId(1234L);


        ProjectDto projectDto2=ProjectDto.projectDto()
                .setId(123L);


        boolean comparisionResult=projectDto1.equals(projectDto2);
        assertFalse(comparisionResult);
    }

    @Test
    public void givenTwoProjectsWithDifferentNames_whenComparedTheResultIsFalse() {
        ProjectDto projectDto1=ProjectDto.projectDto()
                .setName("proiect1");


        ProjectDto projectDto2=ProjectDto.projectDto()
                .setName("proiect2");


        boolean comparisionResult=projectDto1.equals(projectDto2);
        assertFalse(comparisionResult);
    }
    @Test
    public void givenTwoProjectsWithDifferentDescription_whenComparedTheResultIsFalse() {
        ProjectDto projectDto1=ProjectDto.projectDto()
                .setDescription("ppppp");


        ProjectDto projectDto2=ProjectDto.projectDto()
                .setDescription("pppppp");


        boolean comparisionResult=projectDto1.equals(projectDto2);
        assertFalse(comparisionResult);
    }
    @Test
    public void givenTwoProjectsWithDifferentAdministrator_whenComparedTheResultIsFalse() {
        ProjectDto projectDto1=ProjectDto.projectDto()
                .setAdministrator("admin1");


        ProjectDto projectDto2=ProjectDto.projectDto()
                .setAdministrator("admin2");;


        boolean comparisionResult=projectDto1.equals(projectDto2);
        assertFalse(comparisionResult);
    }



}
