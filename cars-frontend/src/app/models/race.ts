import {User} from "./user";

export class Race {

  public name:string;
  public startDate:string;
  public users: User[];

  constructor(name?:string, startDate?:string ){
    this.name=name;
    this.users=[];
    this.startDate=startDate;
  }
}
