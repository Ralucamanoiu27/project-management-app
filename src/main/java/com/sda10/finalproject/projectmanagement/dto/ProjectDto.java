package com.sda10.finalproject.projectmanagement.dto;



import java.util.Objects;

public class ProjectDto {
    public Long id;
    public String name;
    public String description;
    public UserDto administrator;

    private ProjectDto() {
    }

    public static ProjectDto projectDto(){return  new ProjectDto();}

    public ProjectDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProjectDto setName(String name) {
        this.name = name;
        return this;
    }

    public ProjectDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectDto setAdministrator(UserDto administrator) {
        this.administrator = administrator;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(administrator, that.administrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, administrator);
    }


}
