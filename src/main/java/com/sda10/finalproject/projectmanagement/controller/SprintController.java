package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.ProjectMapper;
import com.sda10.finalproject.projectmanagement.dto.SprintDto;
import com.sda10.finalproject.projectmanagement.dto.SprintMapper;
import com.sda10.finalproject.projectmanagement.exception.NotFoundException;
import com.sda10.finalproject.projectmanagement.model.Project;
import com.sda10.finalproject.projectmanagement.model.Sprint;
import com.sda10.finalproject.projectmanagement.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sda10.finalproject.projectmanagement.controller.SprintController.API_SPRINT;


@RestController
@RequestMapping(API_SPRINT)
public class SprintController {
        public static  final String API_SPRINT="/api/sprints";
        private final SprintMapper sprintMapper;
        private final SprintService sprintService;


    @Autowired
    public SprintController(SprintMapper sprintMapper, SprintService sprintService) {
        this.sprintMapper = sprintMapper;
        this.sprintService = sprintService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SprintDto> getSprintById(@PathVariable Long id) {
        Sprint sprint =sprintService.getSprintById(id)
                .orElseThrow(NotFoundException::new);
        SprintDto response=sprintMapper.toDto(sprint);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
        public ResponseEntity<SprintDto> createSprint (@RequestBody SprintDto details) {
        Sprint sprint=sprintMapper.toEntity(details);
        sprint=sprintService.createSprint(sprint);
        SprintDto response=sprintMapper.toDto(sprint);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

        @PutMapping("/{id}")
        public ResponseEntity updateSprint(@PathVariable Long id, @RequestBody SprintDto newDetails){
        Sprint sprint=sprintMapper.toEntity(newDetails);
         sprintService.updateSprint(id,sprint);
         return new ResponseEntity<>(HttpStatus.OK);

        }
        @DeleteMapping("/{id}")
        public ResponseEntity deleteSprint(@PathVariable Long id) {
            sprintService.deleteSprint(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

