package com.sda10.finalproject.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.sda10.finalproject.projectmanagement.model.Project.PROJECTS_TABLE;

@Entity
@Table(name=PROJECTS_TABLE)
public class Project {

    public static final String PROJECTS_TABLE="PROJECTS";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String name;



    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name="administrator_id", referencedColumnName = "id")
    @NotNull
    private User administrator;

    public Long getId() {
        return id;
    }

    public Project setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getAdministrator() {
        return administrator;
    }

    public Project setAdministrator(User administrator) {
        if(administrator.getRole()==Role.ADMIN) {
            this.administrator = administrator;

            return this;
        }
        throw new IllegalArgumentException("This user doesn't have the role admin");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(administrator, project.administrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, administrator);
    }


}
