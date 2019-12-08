package com.sda10.finalproject.projectmanagement.repository;

import com.sda10.finalproject.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
