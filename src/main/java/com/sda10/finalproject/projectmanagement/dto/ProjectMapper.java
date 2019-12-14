package com.sda10.finalproject.projectmanagement.dto;


import com.sda10.finalproject.projectmanagement.model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectDto projectDto){
        Project project = new Project();
        project.setId(projectDto.id);
        project.setName(projectDto.name);
        project.setDescripton(projectDto.description);
        project.setAdministrator(projectDto.administrator);

        return project;
    }

    public ProjectDto toDto(Project project) {
        return ProjectDto.projectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescripton())
                .setAdministrator(project.getAdministrator());
    }

}
