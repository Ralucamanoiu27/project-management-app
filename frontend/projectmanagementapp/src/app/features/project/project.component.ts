import { Project } from './../../shared/model/project';
import { ProjectService } from './project.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  name: string;
  description: string;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
  }

  saveProject() {
    const project = new Project(null, this.name, this.description);
    this.projectService.saveProject(project)
    .subscribe(result => console.log(result));
  }

}
