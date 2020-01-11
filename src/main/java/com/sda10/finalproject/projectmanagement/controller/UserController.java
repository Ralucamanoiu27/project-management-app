package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.UserDto;
import com.sda10.finalproject.projectmanagement.dto.UserMapper;
import com.sda10.finalproject.projectmanagement.exception.NotFoundException;
import com.sda10.finalproject.projectmanagement.model.User;
import com.sda10.finalproject.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.sda10.finalproject.projectmanagement.controller.UserController.API_USERS;

@RestController
@RequestMapping(API_USERS)
public class UserController {

    public static final String API_USERS = "/api/users";
    private final UserMapper userMapper;
    private final UserService userService;


    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(NotFoundException::new);
        UserDto response = userMapper.toDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto details) {
        User user = userMapper.toEntity(details);
        user = userService.createUser(user);
        UserDto response = userMapper.toDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto newDetails) {
        User user = userMapper.toEntity(newDetails);
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDto> searchUserByNameOrEmail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        User user = userService.searchUserByNameOrEmail(name, email)
                .orElseThrow(NotFoundException::new);

        UserDto response = userMapper.toDto(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<UserDto> searchUserByNameOrEmail(@RequestParam(required = false) String name) {
       return userService.searchByUserName(name)
               .stream()
               .map(userMapper::toDto)
               .collect(Collectors.toList());
    }

}
