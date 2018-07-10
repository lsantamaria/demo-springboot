import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as EventSource from 'eventsource';
import {User} from "../models/user";
import {Car} from "../models/car";

@Injectable()
export class UserService {
  private usersList: User[]= new Array();
  private carsList: Car[]= new Array();

  user:User;
  //private baseUrl = 'http://localhost:8080/api/users';
  constructor(private http: HttpClient) { }


  getUsersList(): Observable<any> {
   this.usersList = new Array();
 //   this.carsList = new Array();



    return Observable.create((observer) => {
     /* const eventSource = new EventSource(`${this.baseUrl}`);
      eventSource.onmessage = (event) => {
        console.log('eventSource.onmessage: ', event);
        const json = JSON.parse(event.data);
        this.usersList.push(new Customer(json['id'], json['brand'], json['age'], json['active']));
        observer.next(this.customersList);

      {brand:'enrike', cars:[{brand:'kill', color:'asfa', id:'asfasfa', power:5}]}
      };*/
   //

/*
        let ehhh :Car[] = new Array(2);
        ehhh[0].id= "1";
        ehhh[0].color="blue";
        ehhh[0].brand="honda";
        ehhh[0].power=100;
      ehhh[1].id= "2";
      ehhh[1].color="red";
      ehhh[1].brand="toyota";
      ehhh[1].power=10000;

      let user = new User();
      user.brand="pinga";
      user.cars=ehhh;

      this.usersList.push(user);

      let ehhh2 :Car[] = new Array(2);
      ehhh2[0].id= "1";
      ehhh2[0].color="blue";
      ehhh2[0].brand="honda";
      ehhh2[0].power=100;
      ehhh2[1].id= "2";
      ehhh2[1].color="red";
      ehhh2[1].brand="toyota";
      ehhh2[1].power=10000;

      let user2 = new User();
      user.brand="caca";
      user.cars=ehhh2;
      this.usersList.push(user2);


      console.log(this.usersList);*/

      this.usersList.push({name:'marcelus', cars:[{brand:'honda', color:'azul',  power:23},{brand:'citroen', color:'azul',  power:300},{brand:'bmw', color:'gris',  power:4000}]},{name:'efren', cars:[{brand:'toyota', color:'negro',  power:235}]},{name:'lluis', cars:[{brand:'mercedes', color:'gris', power:323}]},{name:'ivan', cars:[{brand:'mazda', color:'rojo', power:236}]},{name:'marcelus', cars:[{brand:'ola', color:'lala',  power:23}]},{name:'marcelus', cars:[{brand:'ola', color:'lala', power:23}]} );
      observer.next(this.usersList);
    //  eventSource.onerror = (error) => observer.error('eventSource.onerror: ' + error);
     // return () => eventSource.close();
    });
  }

}
