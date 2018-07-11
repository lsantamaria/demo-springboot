import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GLOBAL} from "./global";

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
}
