import { UserComponent } from './features/user/user.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from "./features/project/project.component";
import { HomeComponent } from "./features/home/home.component";
import { ProjectsOverviewComponent } from './features/projects-overview/projects-overview.component';
import { ProjectEditComponent } from './features/project-edit/project-edit.component';


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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
