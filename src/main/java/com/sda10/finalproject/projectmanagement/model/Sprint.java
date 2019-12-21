package com.sda10.finalproject.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name=Sprint.SPRINT_TABLE)
public class Sprint {
// Spring entity from Projects

    public static final String SPRINT_TABLE="SPRINTS";


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", referencedColumnName = "id")
    @NotNull
    private Project project;

    @Column
    @NotNull
    private LocalDate dateFrom;

    @Column
    @NotNull
    private LocalDate dateTo;

    @Column
    private String plannedStoryPoint;

    public Long getId() {
        return id;
    }

    public Sprint setId(Long id) {
        this.id = id;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getPlannedStoryPoint() {
        return plannedStoryPoint;
    }

    public Sprint setProject(Project project) {
        this.project = project;
        return this;
    }

    public Sprint setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Sprint setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public Sprint setPlannedStoryPoint(String plannedStoryPoint) {
        this.plannedStoryPoint = plannedStoryPoint;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equals(id, sprint.id) &&
                Objects.equals(dateFrom, sprint.dateFrom) &&
                Objects.equals(dateTo, sprint.dateTo) &&
                Objects.equals(plannedStoryPoint, sprint.plannedStoryPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, plannedStoryPoint);
    }
}
