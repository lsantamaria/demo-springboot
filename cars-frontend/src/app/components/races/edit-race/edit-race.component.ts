import { Component, OnInit } from '@angular/core';
import * as raceActions from "../../../stores/race.action";
import {AppStore} from "../../../app.store";
import {Store} from "@ngrx/store";
import {Race} from "../../../models/race";


@Component({
  selector: 'app-edit-race',
  templateUrl: './edit-race.component.html',
  styleUrls: ['./edit-race.component.css']
})
export class EditRaceComponent implements OnInit {

  name:string;
  startDate2:any;
  race:Race;

  constructor(private store:Store<AppStore>) { }
  ngOnInit() {
  }
  add(){
    if(this.name && this.startDate2 !=null){
      this.race = new Race();
      this.race.name=this.name;
      this.race.startDate=this.startDate2;
      this.race.users=[];
      console.log("nombre...."+ this.race);
      this.store.dispatch(new raceActions.AddActionRace(this.race));
    }
    this.name="";
    this.startDate2="";
  }
  cancel(){
    this.name="";
    this.startDate2="";
  }
}
