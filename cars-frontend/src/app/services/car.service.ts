import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {GLOBAL} from "./global";
import {Car} from "../models/car";

@Injectable()
export class CarService {

  URL:string;
  constructor(private http: HttpClient) {
    this.URL= GLOBAL.url;
  }

  addCar(id:string, addCar:Car){
    let json = JSON.stringify(addCar);
    let headers = new HttpHeaders();
    let params = json;
    headers =  headers.set('Content-Type','application/json');
    return this.http.post(this.URL+"/users/"+id+"/cars", params, {headers: headers})
      .map(res => res );
  }
}
