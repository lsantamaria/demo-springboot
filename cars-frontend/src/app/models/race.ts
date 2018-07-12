import {User} from "./user";

export class Race {

  public name:string;
  public startDate:Date;
  public users: User[];

  constructor(name?:string, startDate?:Date ){
    this.name=name;
    this.users=[];
    this.startDate=startDate;
  }
}
