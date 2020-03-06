import { Project } from './project';
import {LocalNgModuleData} from "@angular/compiler-cli/src/ngtsc/scope";
import {MatDatepicker} from "@angular/material/datepicker";


export class Sprint {

  constructor(public id: number,
              public project: Project,
              public dateFrom: Date,
              public dateTo: Date,
              public plannedStoryPoint: string){

  }
}
