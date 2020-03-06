import { environment } from 'src/environments/environment';
import { User } from './../../shared/model/user';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from 'src/app/shared/model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {



private readonly  TASK_API = `${environment.serverApiUrl}/api/tasks`;
  constructor(private httpClient: HttpClient) {}

  saveTask(task: Task): Observable<Task> {
    return this.httpClient.post<Task>(this.TASK_API, task);
  }

  searchTaskByName(nameParam: string) {
    return this.httpClient.get<Task[]>(this.TASK_API + '/search', {
    params: { name: nameParam }
  });

}

}
