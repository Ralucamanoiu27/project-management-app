package com.sda10.finalproject.projectmanagement.repository;

import com.sda10.finalproject.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findByUserName(String name);
    List<User> findByUserNameContaining(String nameSubstring);
    Optional<User> findByEmail (String email);
}
