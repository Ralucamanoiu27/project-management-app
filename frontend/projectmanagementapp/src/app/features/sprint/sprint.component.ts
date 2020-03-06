import { Component, OnInit } from '@angular/core';

import { Project } from './../../shared/model/project';
import {FormControl} from "@angular/forms";
import {ProjectService} from "../project/project.service";
import { Observable } from 'rxjs';

import {MatDatepickerModule} from '@angular/material/datepicker';
import {Router} from "@angular/router";


import {SprintService} from "./sprint.service";
import {Sprint} from "../../shared/model/sprint";
import {flatMap, map, startWith} from "rxjs/operators";
import { UserService } from '../user/user.service';



@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.css']


})

export class SprintComponent implements OnInit {

  project: Project;
  dateFrom: Date;
  dateTo: Date;
  plannedStoryPoint: string;


  myControl = new FormControl();
  filteredOptions: Observable<Project[]>;



  constructor(private sprintService: SprintService,
              private projectService: ProjectService, 
              private userService: UserService, 
              private router: Router) { }

              //copiezi de la porject mapp
  ngOnInit() {
    // @ts-ignore
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.name),
        flatMap(name => this.projectService.searchProjectByName(name))
      );


  }


  displayFn(project?: Project): string | undefined {
    return project ? project.name : undefined;
  }

  saveSprint() {
    const project = this.myControl.value;
    const sprint = new Sprint(null, project, this.dateFrom, this.dateTo, this.plannedStoryPoint );
    this.sprintService.saveSprint(sprint)
      // .subscribe(result => console.log(result));
      .subscribe(result => console.log('ok'),
        error => console.log(error));

  }

}





