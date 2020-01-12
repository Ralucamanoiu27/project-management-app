import { User } from './user';

export class Project {

  constructor(public id: number,
              public name: string,
              public description: string,
              public administrator: User){

  }
}
