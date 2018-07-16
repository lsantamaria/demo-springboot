import { Component, OnInit } from '@angular/core';
import * as raceActions from "../../../stores/race.action";
import {AppStore} from "../../../app.store";
import {Store} from "@ngrx/store";
import {Race} from "../../../models/race";
import {RaceService} from "../../../services/race.service";


@Component({
  selector: 'app-edit-race',
  templateUrl: './edit-race.component.html',
  styleUrls: ['./edit-race.component.css']
})
export class EditRaceComponent implements OnInit {

  name:string;
  startDate2:any;
  race:Race;

  constructor(private store:Store<AppStore>, private raceService:RaceService) {}
  ngOnInit() {
  }
  add(){
    if(this.name && this.startDate2 !=null){
      this.race = new Race();
      this.race.name=this.name;
      var month = this.startDate2.getMonth()+1;
      var day = this.startDate2.getUTCDate()+1;
      var date;

      if(month < 10){
        month = "0" + month;
      }
      if(day < 10){
        day = "0" + day;
      }
      date = ""+this.startDate2.getFullYear()+"-"+month+"-"+day+"";

      this.race.startDate=date;
      console.log("nombre...."+ this.race);
      this.raceService.addRace(this.race).subscribe((response:any)=>{
        console.log(response);
        this.store.dispatch(new raceActions.AddActionRace(response));
      });

    }
    this.name="";
    this.startDate2=null;
  }
  cancel(){
    this.name="";
    this.startDate2=null;
  }
}
