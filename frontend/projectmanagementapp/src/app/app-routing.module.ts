import { UserComponent } from './features/user/user.component';
import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from "./features/project/project.component";
import { HomeComponent } from "./features/home/home.component";
import { ProjectsOverviewComponent } from './features/projects-overview/projects-overview.component';
import { ProjectEditComponent } from './features/project-edit/project-edit.component';
import {SprintComponent} from "./features/sprint/sprint.component";
import { TaskComponent } from './features/task/task.component';


const routes: Routes = [
  {
    path: 'project',
    component: ProjectComponent
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'projects-overview',
    component: ProjectsOverviewComponent
  },
  {
    path: 'projects/:id/edit',
    component: ProjectEditComponent
  },
  {
    path: 'sprint',
    component: SprintComponent
  },
  {
    path: 'task',
    component: TaskComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
