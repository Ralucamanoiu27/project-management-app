package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.UnitTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/// facut de Cristina -- neverificat si incomplet
public class SprintDtoTest extends UnitTest {


    @Test
    public  void givenTwoEqualSprint_whenCompared_theResultIsTrue() {
        SprintDto sprintDto1=SprintDto.sprintDto()
                .setId(123L)
                .setProjectDto(ProjectDto.projectDto().setDescription("abcde"))
                ;

        SprintDto sprintDto2=SprintDto.sprintDto()
                .setId(123L)
               ;

        boolean comparisionResult=sprintDto1.equals(sprintDto2);
        assertTrue(comparisionResult);

    }


    @Test
    public void givenTwoSprintWithDifferentIds_whenComparedTheResultIsFalse() {
        SprintDto sprintDto1=SprintDto.sprintDto()
                .setId(1255L);


        SprintDto sprintDto2=SprintDto.sprintDto()
                .setId(132L);


        boolean comparisionResult=sprintDto1.equals(sprintDto2);
        assertFalse(comparisionResult);
    }

    @Test
    public void givenTwoSprintWithDifferentNames_whenComparedTheResultIsFalse() {
        SprintDto sprintDto1=SprintDto.sprintDto()
                .setId(1254L)
                .setPlannedStoryPoint("test1")
                .setProjectDto(ProjectDto.projectDto().setName("proiect1"))
                ;

        SprintDto sprintDto2=SprintDto.sprintDto()
                .setId(1255L)
                .setPlannedStoryPoint("test2")
                ;

        boolean comparisionResult=sprintDto1.equals(sprintDto2);
        assertFalse(comparisionResult);
    }

    @Test
    public void givenTwoSPrintWithDifferentDescription_whenComparedTheResultIsFalse() {
        SprintDto sprintDto1=SprintDto.sprintDto()
                .setProjectDto(ProjectDto.projectDto().setDescription("tfugigih"));

        SprintDto sprintDto2=SprintDto.sprintDto()
                .setProjectDto(ProjectDto.projectDto().setDescription("tfugih"));

        boolean comparisionResult=sprintDto1.equals(sprintDto2);
        assertFalse(comparisionResult);
    }


}
