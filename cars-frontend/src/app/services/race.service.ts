import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GLOBAL} from "./global";
import {Car} from "../models/car";
import {Race} from "../models/race";

@Injectable()
export class RaceService {


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

  getMyRace(userId) {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    return this.http.get(this.URL +'/users/'+userId+"/races",  {headers: headers})
      .map(res => res );
  }

  addRace(addRace:Race){
    let json = JSON.stringify(addRace);
    let headers = new HttpHeaders();
    let params = json;
    headers =  headers.set('Content-Type','application/json');
    return this.http.post(this.URL+"/races", params, {headers: headers})
      .map(res => res );
  }

  addUserToRace(object_ids){
    let json = JSON.stringify(object_ids.idUser);
    let headers = new HttpHeaders();
    let params = json;
    headers =  headers.set('Content-Type','application/json');
    return this.http.post(this.URL+"/races/"+object_ids.idRace+"/users", params, {headers: headers})
    .map(res => res );
  }

  deleteRace(id:string){
    let headers = new HttpHeaders();
    headers =  headers.set('Content-Type','application/json');
    return this.http.delete(this.URL+"/races/"+id, {headers: headers})
    .map(res => res );
  }

}
