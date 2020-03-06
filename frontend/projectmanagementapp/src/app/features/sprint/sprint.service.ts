import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sprint} from "../../shared/model/sprint";
import {User} from "../../shared/model/user";
import {Project} from "../../shared/model/project";

@Injectable({
  providedIn: 'root'
})
export class SprintService {


  private readonly  SPRINT_API = `${environment.serverApiUrl}/api/sprints`;

  constructor(private httpClient: HttpClient) {}

  saveSprint(sprint: Sprint): Observable<Sprint> {
    return this.httpClient.post<Sprint>(this.SPRINT_API, sprint);
  }
  getAllSprints(): Observable<Sprint[]> {
    return this.httpClient.get<Sprint[]>(this.SPRINT_API);

  }

  deleteSprints(id: number): Observable<Sprint> {
    return this.httpClient.delete<Sprint>(this.SPRINT_API + `/${id}` );
  }
  getSprintById(id: number): Observable<Sprint> {
    return this.httpClient.get<Sprint>(this.SPRINT_API + `/${id}`);

  }
  updateSprint(id: number, sprint: Sprint): Observable<Sprint> {
    return this.httpClient.put<Sprint>(this.SPRINT_API + `/${id}`, sprint);

  }



}
