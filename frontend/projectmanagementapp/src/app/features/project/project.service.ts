import { Project } from './../../shared/model/project';

import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

private readonly  PROJECT_API = `${environment.serverApiUrl}/api/projects`;

  constructor(private httpClient: HttpClient) {}

  saveProject(project: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.PROJECT_API, project);
  }
  getAllProjects(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(this.PROJECT_API);

  }

  deleteProject(id: number): Observable<Project> {
    return this.httpClient.delete<Project>(this.PROJECT_API + `/${id}` );
  }
  getProjectById(id: number): Observable<Project> {
    return this.httpClient.get<Project>(this.PROJECT_API + `/${id}`);

  }
  updateProject(id: number, project: Project): Observable<Project> {
    return this.httpClient.put<Project>(this.PROJECT_API + `/${id}`, project);

  }
}


