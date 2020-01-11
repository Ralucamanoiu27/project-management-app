import { environment } from 'src/environments/environment';
import { User } from './../../shared/model/user';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {



private readonly  USER_API = `${environment.serverApiUrl}/api/users`;
  constructor(private httpClient: HttpClient) {}

  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.USER_API, user);
  }

}
