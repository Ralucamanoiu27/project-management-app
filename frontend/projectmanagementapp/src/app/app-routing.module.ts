import { UserComponent } from './features/user/user.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectComponent } from "./features/project/project.component";
import { HomeComponent } from "./features/home/home.component";


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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
