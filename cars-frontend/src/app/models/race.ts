import {User} from "./user";

export class Race {

  public name:string;
  public users: User[];

  constructor(name?:string){
    this.name=name;
    this.users=[];
  }
}
