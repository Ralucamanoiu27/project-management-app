package com.sda10.finalproject.projectmanagement.service;

import com.sda10.finalproject.projectmanagement.model.Sprint;
import com.sda10.finalproject.projectmanagement.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SprintService {
    private final SprintRepository sprintRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository=sprintRepository;
    }

    public Optional<Sprint> getSprintById(Long id) {
        return sprintRepository.findById(id);
    }
    public Sprint createSprint(Sprint sprint) {
        if (!sprint.getDateFrom().isBefore(sprint.getDateTo())) {
            throw new IllegalArgumentException("Sprint interval is not valid");
        }
        if (sprintOverLapsWithExistingSprint(sprint)) {
            throw new IllegalArgumentException("Sprint interval overlaps with existing sprints");

        }
        return sprintRepository.save(sprint);
    }

    private boolean sprintOverLapsWithExistingSprint(Sprint sprint) {
        List<Sprint> existingSprints=sprintRepository.findSprintsByProject(sprint.getProject());
        for (Sprint existingSprint : existingSprints) {
            if (sprint.getDateFrom().isAfter(existingSprint.getDateFrom())
            && sprint.getDateFrom().isBefore(existingSprint.getDateTo())) {
                return true;
            }
            if (sprint.getDateTo().isAfter(existingSprint.getDateFrom()) &&
            sprint.getDateTo().isBefore(existingSprint.getDateTo())) {
                return true;
            }
        }
        return false;
    }


    public Sprint updateSprint(Long id, Sprint sprint) {
        sprintRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        sprint.setId(id);
        return sprintRepository.save(sprint);
    }
    public void deleteSprint(Long id) {
        Sprint existingSprint = sprintRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        sprintRepository.delete(existingSprint);
    }

}
