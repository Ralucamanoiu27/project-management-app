package com.sda10.finalproject.projectmanagement.dto;

import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {
    private final ProjectMapper projectMapper;

    public SprintMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public Sprint toEntity(SprintDto sprintDto) {
        Sprint sprint = new Sprint();
        Project project = projectMapper.toEntity(sprintDto.projectDto);

        sprint.setProject(project)
                .setDateFrom(sprintDto.dateFrom)
                .setDateTo(sprintDto.dateTo)
                .setPlannedStoryPoint(sprintDto.plannedStoryPoint);

        return sprint;
    }
/// de verificat
    public SprintDto toDto(Sprint sprint) {
        ProjectDto projectDto = projectMapper.toDto(sprint.getProject());
        return SprintDto.sprintDto()
                .setId(sprint.getId())
                .setProjectDto(projectDto)
                .setDateFrom(sprint.getDateFrom())
                .setDateTo(sprint.getDateTo())
                .setPlannedStoryPoint(sprint.getPlannedStoryPoint());
    }
}


