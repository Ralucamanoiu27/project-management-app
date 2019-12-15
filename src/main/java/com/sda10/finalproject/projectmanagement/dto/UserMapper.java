package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserMapper {

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.id);
        user.setUserName(userDto.userName);
        user.setDisplayedName(userDto.displayedName);
        user.setEmail(userDto.email);
        user.setPassword(userDto.password);
        user.setRole(userDto.role);

        return user;
    }

    public UserDto toDto(User user) {
        return UserDto.userDto()
                .setId(user.getId())
                .setDisplayedName(user.getDisplayedName())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setRole(user.getRole());
    }
}
