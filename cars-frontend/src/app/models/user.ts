import {Car} from "./car";


export class User {
public name: string;
public  cars: Car[];

constructor(name?:string){
  this.name=name;
  this.cars=[];
  }
}
