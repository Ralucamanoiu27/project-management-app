package com.sda10.finalproject.projectmanagement.dto;


import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final UserMapper userMapper;

    public ProjectMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Project toEntity(ProjectDto projectDto){
        Project project = new Project();


        User user = userMapper.toEntity(projectDto.administrator);

        project.setAdministrator(user);
        project.setId(projectDto.id);
        project.setName(projectDto.name);
        project.setDescripton(projectDto.description);


        return project;
    }

    public ProjectDto toDto(Project project) {
        UserDto userDto = userMapper.toDto(project.getAdministrator());
        return ProjectDto.projectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescripton())
                .setAdministrator(userDto);
    }

}
