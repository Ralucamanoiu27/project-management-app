package com.sda10.finalproject.projectmanagement.service;

import com.sda10.finalproject.projectmanagement.model.Sprint;
import com.sda10.finalproject.projectmanagement.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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
        return sprintRepository.save(sprint);
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
