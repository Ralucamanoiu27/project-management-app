import { ProjectService } from './../project/project.service';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/shared/model/project';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-projects-overview',
  templateUrl: './projects-overview.component.html',
  styleUrls: ['./projects-overview.component.css']
})
export class ProjectsOverviewComponent implements OnInit {

  projects: Observable<Project[]>;
  columnsToDisplay: string[];

  constructor(private projectService: ProjectService ) {}

  ngOnInit() {
    this.projects = this.projectService.getAllProjects();
    this.columnsToDisplay = ['id', 'name', 'description', 'administrator','actions'];

  }
deleteProject(id: number){
  this.projectService.deleteProject(id)
  .subscribe(result => this.projects = this.projectService.getAllProjects());
}

}
