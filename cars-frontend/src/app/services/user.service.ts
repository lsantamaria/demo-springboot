import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as EventSource from 'eventsource';
import {User} from "../models/user";
import {Car} from "../models/car";
import {GLOBAL} from "./global";

@Injectable()
export class UserService {
  private usersList: User[]= new Array();
  private carsList: Car[]= new Array();

  user:User;
  //private baseUrl = 'http://localhost:8080/api/users';

  URL:string;
  constructor(private http: HttpClient) {
    this.URL= GLOBAL.url;
  }
  getRaces() {
    return this.http.get(this.URL+'/races')
      .map(res => res );
  }

  getRace(userId) {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.get(this.URL +'/races/'+userId,  {headers: headers})
      .map(res => res );
  }

  login(user_to_login){
    let json = JSON.stringify(user_to_login);
    let headers = new HttpHeaders();
    let params = json;
    headers =  headers.set('Content-Type','application/json');
    return this.http.post('http://localhost:8080/login', params, {headers: headers})
      .map(res => res );
  }

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
*/
      this.usersList.push({name:'marcelus', password:'agasg', email:"asaasgas", cars:[{brand:'honda', color:'azul',  power:23},{brand:'citroen', color:'azul',  power:300},{brand:'bmw', color:'gris',  power:4000}]});
      observer.next(this.usersList);
    //  eventSource.onerror = (error) => observer.error('eventSource.onerror: ' + error);
     // return () => eventSource.close();
    });
  }

}
