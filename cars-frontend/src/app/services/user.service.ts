import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {User} from "../models/user";
import {Car} from "../models/car";
import {GLOBAL} from "./global";
import {environment} from "../../environments/environment";

@Injectable()
export class UserService {
  private usersList: User[]= [];
  private carsList: Car[]= [];

  user:User;

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
    let body = new URLSearchParams();
    body.set('email', user_to_login.email);
    body.set('password', user_to_login.password);
    let options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };
    return this.http.post(environment.apiUrl + '/login', body.toString(), options)
      .map(res => res );
  }

  register(user_to_register){
    let headers = new HttpHeaders();
    let params = JSON.stringify(user_to_register);
    headers =  headers.set('Content-Type','application/json');
    return this.http.post(environment.apiUrl + '/register', params, {headers: headers});
  }


  getUsersList(): Observable<any> {
   this.usersList = [];
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
