package com.sda10.finalproject.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table (name=Task.TASK_TABLE)
public class Task {
    public static final String TASK_TABLE="TASKS";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String nameTask;

    @Column
    @NotNull
    private String descriptionTask;

    @ManyToOne
    @JoinColumn(name="sprint_id", referencedColumnName = "id")
    @NotNull
    private Sprint sprint;

    @Column
    @NotNull
    private String dificulty;

    @Column
    @NotNull
    private String storyPoints;

    @Column
    @NotNull
    private Progress progress;

    @ManyToOne
    @JoinColumn(name="assignPerson_id", referencedColumnName = "id")
    @NotNull
    private User assignPerson; // users

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNameTask() {
        return nameTask;
    }

    public Task setNameTask(String nameTask) {
        this.nameTask = nameTask;
        return this;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public Task setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
        return this;
    }

    public String getDificulty() {
        return dificulty;
    }

    public Task setDificulty(String dificulty) {
        this.dificulty = dificulty;
        return this;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public Task setStoryPoints(String storyPoints) {
        this.storyPoints = storyPoints;
        return this;
    }

    public Progress getProgress() {
        return progress;
    }

    public Task setProgress(Progress progress) {
        this.progress = progress;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(nameTask, task.nameTask) &&
                Objects.equals(descriptionTask, task.descriptionTask) &&
                Objects.equals(sprint, task.sprint) &&
                Objects.equals(dificulty, task.dificulty) &&
                Objects.equals(storyPoints, task.storyPoints) &&
                progress == task.progress &&
                Objects.equals(assignPerson, task.assignPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTask, descriptionTask, sprint, dificulty, storyPoints, progress, assignPerson);
    }
}
