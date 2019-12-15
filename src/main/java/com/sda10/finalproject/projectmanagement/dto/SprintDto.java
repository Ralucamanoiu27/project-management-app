package com.sda10.finalproject.projectmanagement.dto;

import java.util.Objects;

public class SprintDto {

   public Long id;


    public ProjectDto projectDto;


   public String dateFrom;
   public String dateTo;
   public String plannedStoryPoint;

   private SprintDto() {
   }
    public static SprintDto sprintDto() {
       return new SprintDto();
    }

    public SprintDto setId(Long id) {
        this.id = id;
        return this;
    }

    public SprintDto setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public SprintDto setDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public SprintDto setPlannedStoryPoint(String plannedStoryPoint) {
        this.plannedStoryPoint = plannedStoryPoint;
        return this;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public SprintDto setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDto sprintDto = (SprintDto) o;
        return Objects.equals(id, sprintDto.id) &&
                Objects.equals(dateFrom, sprintDto.dateFrom) &&
                Objects.equals(dateTo, sprintDto.dateTo) &&
                Objects.equals(plannedStoryPoint, sprintDto.plannedStoryPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, plannedStoryPoint);
    }
}
