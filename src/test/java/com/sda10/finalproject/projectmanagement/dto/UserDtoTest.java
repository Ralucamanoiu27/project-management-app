package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.UnitTest;
import com.sda10.finalproject.projectmanagement.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDtoTest extends UnitTest {

    @Test
    public void givenTwoEqualUser_whenCompared_theResultIsTrue() {
        UserDto userDto1 = UserDto.userDto()
                .setId(123L)
                .setUserName("a.test")
                .setDisplayedName("cristina")
                .setEmail("123@yahoo.com")
                .setPassword("123")
                .setRole(Role.USER);


        UserDto projectDto2 = UserDto.userDto()
                .setId(123L)
                .setUserName("a.test")
                .setDisplayedName("cristina")
                .setEmail("123@yahoo.com")
                .setPassword("123")
                .setRole(Role.USER);

        boolean comparisionResult = userDto1.equals(projectDto2);
        assertTrue(comparisionResult);

    }

    @Test
    public void givenTwoUserWithDifferentIds_whenComparedTheResultIsFalse() {
        UserDto userDto1 = UserDto.userDto()
                .setId(1234L);


        ProjectDto userDto2 = ProjectDto.projectDto()
                .setId(123L);


        boolean comparisionResult = userDto1.equals(userDto2);
        assertFalse(comparisionResult);
    }

    @Test
    public void givenTwoUsersWithDifferentNames_whenComparedTheResultIsFalse() {
        UserDto userDto1 = UserDto.userDto()
                .setUserName("a.test");


        UserDto userDto2 = UserDto.userDto()
                .setUserName("b.test");


        boolean comparisionResult = userDto1.equals(userDto2);
        assertFalse(comparisionResult);
    }

    @Test
    public void givenTwoProjectsWithDifferentDescription_whenComparedTheResultIsFalse() {
        UserDto userDto1 = UserDto.userDto()
                .setEmail("cristina1@yahoo.com");


        UserDto userDto2 = UserDto.userDto()
                .setDisplayedName("cristina2@yahoo.com");


        boolean comparisionResult = userDto1.equals(userDto2);
        assertFalse(comparisionResult);
    }

}

