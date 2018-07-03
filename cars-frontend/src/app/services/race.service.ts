import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { HttpClient } from '@angular/common/http';
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
}
