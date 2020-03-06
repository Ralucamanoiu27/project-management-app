import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/model/user';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';
import { TaskService } from './task.service';
import { Router } from '@angular/router';
import { Task } from 'src/app/shared/model/task';
import { startWith, map, flatMap } from 'rxjs/operators';
import { Sprint } from 'src/app/shared/model/sprint';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {



    nameTask: string;
    descriptionTask: string;
    sprint: Sprint;
    dificulty: string;
    storyPoints: string;
    progress: string;
    assignPerson: User;
    
    dificultes: string[] = ['UNU','DOI','TREI','PATRU','CINCI'];
    progresses: string[] = ['BACKLOG', 'TO_DO', 'IN_PROGRESS', 'QA', 'DONE'];

  myControl = new FormControl();
  filteredOptions: Observable<User[]>;


  constructor(private taskService: TaskService,
              private userService: UserService, private router: Router) { }



  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof value === 'string' ? value : value.name),
        flatMap(name => this.userService.searchUserByName(name))
      );
  }

  displayFn(user?: User): string | undefined {
    return user ? user.displayedName : undefined;
  }

  saveTask() {
    const user = this.myControl.value;
    const task = new Task(null, this.nameTask, this.descriptionTask, this.sprint, this.dificulty, this.storyPoints,this.progress, user);
    this.taskService.saveTask(task)
      // .subscribe(result => console.log(result));
      .subscribe(result => console.log('ok'),
        error => console.log(error));
    //return to table
   //this.router.navigateByUrl('/projects-overview');


  }
}