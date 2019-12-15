package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.model.Role;
import com.sda10.finalproject.projectmanagement.model.User;

import java.util.Objects;

public class UserDto {



    public String userName;

    public String password;

    public String email;

    public String displayedName;

    public Role role;

    public Long id;

    private UserDto() {
    }

    public static UserDto userDto(){return  new UserDto();}

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }
    public String getUserName() {
        return userName;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public UserDto setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userName, userDto.userName) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(displayedName, userDto.displayedName) &&
                role == userDto.role &&
                Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, email, displayedName, role, id);
    }
}
