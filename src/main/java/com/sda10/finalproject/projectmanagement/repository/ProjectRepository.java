package com.sda10.finalproject.projectmanagement.repository;

import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//    Optional<Project> findByProjectName(String name);
//    List<Project> findByProjectNameContaining(String nameSubstring);


}
