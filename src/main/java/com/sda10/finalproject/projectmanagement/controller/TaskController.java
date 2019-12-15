package com.sda10.finalproject.projectmanagement.controller;

import com.sda10.finalproject.projectmanagement.dto.TaskDto;
import com.sda10.finalproject.projectmanagement.dto.TaskMapper;
import com.sda10.finalproject.projectmanagement.exception.NotFoundException;
import com.sda10.finalproject.projectmanagement.model.Task;
import com.sda10.finalproject.projectmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sda10.finalproject.projectmanagement.controller.TaskController.API_TASKS;

@RestController
@RequestMapping(API_TASKS)
public class TaskController {
    public static final String API_TASKS="/api/tasks";
    //private final TaskController



        private final TaskMapper taskMapper;
        private final TaskService taskService;


        @Autowired
        public TaskController(TaskMapper taskMapper, TaskService taskService) {
            this.taskMapper = taskMapper;
            this.taskService = taskService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
            Task task =taskService.getTaskById(id)
                    .orElseThrow(NotFoundException::new);
            TaskDto response=taskMapper.toDto(task);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<TaskDto> createUser (@RequestBody TaskDto details) {
            Task task=taskMapper.toEntity(details);
            task=taskService.createTask(task);
            TaskDto response=taskMapper.toDto(task);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskDto newDetails){
            Task task=taskMapper.toEntity(newDetails);
            taskService.updateTask(id,task);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        @DeleteMapping("/{id}")
        public ResponseEntity deleteTask(@PathVariable Long id) {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

