package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.UnitTest;
import com.sda10.finalproject.projectmanagement.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectMapperTest extends UnitTest {

    private ProjectMapper projectMapper;

    @BeforeEach
    public void before(){
        UserMapper userMapper= new UserMapper();
        projectMapper = new ProjectMapper(userMapper);
    }

    @Test
    void givenProjectDto_whenToEntity_ThenProjectIsCreatedCorrectly(){
        long expectedId= 123L;
        String expectedName="proiect1";
        String expectedDescription="aaaa";
        UserDto expectedAministrator = UserDto.userDto()
                .setRole(Role.ADMIN)
                .setUserName("admin");

        ProjectDto expected = ProjectDto.projectDto()
                .setId(expectedId)
                .setName(expectedName)
                .setDescription(expectedDescription)
                .setAdministrator(expectedAministrator);
    }
}
