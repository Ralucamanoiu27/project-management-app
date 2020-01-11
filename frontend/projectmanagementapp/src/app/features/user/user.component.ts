import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/model/user';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  userName: string;
  password: string;
  email: string;
  displayedName: string;
  role: string;
  roles: string[] = ['USER', 'ADMIN'];
  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  saveUser() {
    const user = new User(null, this.userName, this.password, this.email, this.displayedName, this.role);

    this.userService.saveUser(user)
      .subscribe(result => console.log(result));
  }

}




