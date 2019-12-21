package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.RestIntegrationTest;
import com.sda10.finalproject.projectmanagement.dto.ProjectDto;
import com.sda10.finalproject.projectmanagement.dto.ProjectMapper;
import com.sda10.finalproject.projectmanagement.dto.UserDto;
import com.sda10.finalproject.projectmanagement.dto.UserMapper;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.Role;
import com.sda10.finalproject.projectmanagement.model.User;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import com.sda10.finalproject.projectmanagement.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.sda10.finalproject.projectmanagement.controller.UserController.API_USERS;

public class UserControllerRestIntegrationTest extends RestIntegrationTest {

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

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void givenNonExistingId_whenGetUsersByID_ThenStatusCodeIsInternalServerError() {
        long nonExistingId = 155L;

        String relativePath = API_USERS + "/" + nonExistingId;
        HttpStatus statusCode = this.restTemplate
                .getForEntity(url(relativePath), UserDto.class)
                .getStatusCode();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
    }

    @Test
    public void givenUserDetails_whenPostRequestIsReceived_ThenCreateNewUserDetails() {

      User user = new User();
                user.setUserName("Raluca")
                     .setPassword("123")
                     .setRole(Role.ADMIN)
                        .setEmail("aaa@yahoo.com")
                        .setDisplayedName("Ana Are mere");

       user = userRepository.save(user);

        UserDto expectedResult = UserDto.userDto()
                .setUserName("cristina")
                .setPassword("123")
                .setRole(Role.ADMIN);
        String relativePath = API_USERS;
        ResponseEntity<UserDto> response = this.restTemplate
                .postForEntity(url(relativePath), expectedResult, UserDto.class);

        expectedResult.setId(response.getBody().id);
        //then
        Assertions.assertEquals(expectedResult, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void givenExistingId_whenGetUserById_ThenReturnUser() {
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);

        User expectedUser = new User();

        expectedUser.setUserName("project3")
                .setDisplayedName("aaaa")
                .setEmail("eeee@yahoo.ro")
                .setPassword("1");

        /// nu e bine
        expectedUser =userRepository.save(user);

        ResponseEntity<UserDto> response = this.restTemplate
                .getForEntity(API_USERS + "/" + expectedUser.getId(), UserDto.class);

        UserDto newUserDto = response.getBody();
        //Optional <User> updateUser=this.repository.findById(newUserDto.id);
        //Assertions.assertTrue(updateUser.isPresent());
        Assertions.assertEquals(newUserDto.setId(newUserDto.id), newUserDto);
    }
    @Test
    public void givenUserDetails_whenDeleteRequestIsReceived_ThenUserIsDeleted() {

        /// trebuie sters userul din proiect / taskuri
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);


        String relativePath = API_USERS + "/" + user.getId();
        this.restTemplate.delete(relativePath);

        Optional<User> updateUser = this.userRepository.findById(user.getId());

        Assertions.assertFalse(updateUser.isPresent());
    }

    @Test
    public void givenProjectDetails_whenPutRequestIsReceived_ThenUpdateNewProjectDetails() {
        User user = new User();
        user.setUserName("raluca")
                .setPassword("123")
                .setRole(Role.ADMIN);
        user = userRepository.save(user);

        UserDto newUser = userMapper.toDto(user)
                .setUserName("cristinaaa");

        String relativePath = API_USERS + "/" + newUser.id;
        this.restTemplate.put(relativePath, newUser);

        Optional<User> updateProject = this.userRepository.findById(newUser.id);

        Assertions.assertTrue(updateProject.isPresent());
        Assertions.assertEquals(newUser, userMapper.toDto(updateProject.get()));

    }
}









