import { Project } from './../../shared/model/project';

import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

private readonly  PROJECT_API = `${environment.serverApiUrl}/project`;
  constructor(private httpClient: HttpClient) {}

  saveProject(project: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.PROJECT_API, project);
  }
}


