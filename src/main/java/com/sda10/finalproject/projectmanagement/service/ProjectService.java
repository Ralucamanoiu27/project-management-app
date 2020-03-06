package com.sda10.finalproject.projectmanagement.service;

import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.User;
import com.sda10.finalproject.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project project) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            project.setId(id);
            return projectRepository.save(project);
        } else {
            throw new RuntimeException("Project with id does not exist: " + id);
        }
    }
    public void deleteProject (Long id) {
        Project existingProject = projectRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        projectRepository.delete(existingProject);
    }


//    public Optional<Project> searchProjectByName(String name ) {
//        if (name != null) {
//            return projectRepository.findByProjectName(name);
//        }
//
//        return Optional.empty();
//    }

//    public Optional<Project> searchProjectByNameProject(String project){
//        if(project != null){
//            return projectRepository.findByProjectName(project);
//        }
//        return  Optional.empty();
//    }
//    public List<Project> searchByProjectName(String name) {
//        return projectRepository.findByProjectNameContaining(name);
//    }

      public List<Project> findAll() {return projectRepository.findAll();}


}
