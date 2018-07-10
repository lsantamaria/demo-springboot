import {Component, OnInit, ViewChildren, OnDestroy} from '@angular/core';
import {Store} from "@ngrx/store";
import {User} from "../../models/user";
import {Car} from "../../models/car";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {UserService} from "../../services/user.service";
import {Subscription} from "../../../../node_modules/rxjs";
import {RaceService} from "../../services/race.service";
import {Race} from "../../models/race";
import {MatMenuTrigger} from "@angular/material";
import * as raceActions from "../../stores/race.action";

@Component({
  selector: 'app-races',
  templateUrl: './races.component.html',
  styleUrls: ['./races.component.css']
})
export class RacesComponent implements OnInit, OnDestroy {

  users:User[];
  cars: Car[];
  races: any;
  races2:any;
  selectedTabIndex: number;

  private raceStateSubscription:Subscription;

  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,private racesService:RaceService) {

  }
//https://stackoverflow.com/questions/46902829/how-can-i-load-components-added-in-tab-using-angular4

  ngOnInit() {
    this.selectedTabIndex = 0;
    this.racesService.getRaces().subscribe(res =>{
      this.races=res;

      for (let i in this.races) {
        console.log(this.races[i]); // "0", "1", "2",
        this.store.dispatch(new raceActions.AddActionRace(this.races[i]));
      }
      console.log(this.races);
    });
  }
  onOpenMenu(id:any): void {
    console.log(id);

  }

  OpenTab(){

  }


  onSelectChange(event) {
    if (event.index == 1) {
      console.log('Tab1 is selected!');
      this.raceStateSubscription = this.store.select('raceState').subscribe(raceState => {

        if (raceState.races) {
          this.races = raceState.races;
          console.log(this.races);
        }
        //this.router.navigate(["login"]);
      });
    }
  }
    ngOnDestroy(){
    /*  if(this.raceStateSubscription){
        this.raceStateSubscription.unsubscribe();
      }*/
    }

}
