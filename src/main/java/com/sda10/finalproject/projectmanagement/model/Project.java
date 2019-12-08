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
    private String descripton;

    @Column
    private String administrator;

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

    public String getDescripton() {
        return descripton;
    }

    public Project setDescripton(String descripton) {
        this.descripton = descripton;
        return this;
    }

    public String getAdministrator() {
        return administrator;
    }

    public Project setAdministrator(String administrator) {
        this.administrator = administrator;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project proiect = (Project) o;
        return Objects.equals(id, proiect.id) &&
                Objects.equals(name, proiect.name) &&
                Objects.equals(descripton, proiect.descripton) &&
                Objects.equals(administrator, proiect.administrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, descripton, administrator);
    }


}
