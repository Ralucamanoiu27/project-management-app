package com.sda10.finalproject.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import static com.sda10.finalproject.projectmanagement.model.User.USERS_TABLE;

@Entity
@Table(name=USERS_TABLE)
public class User {
    public static final String USERS_TABLE="USERS";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String password;

    @Column

    private String email;

    @Column

    private String displayedName;

    @Column
    @NotNull
    private Role role;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public User setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(displayedName, user.displayedName) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, displayedName, role);
    }
}
