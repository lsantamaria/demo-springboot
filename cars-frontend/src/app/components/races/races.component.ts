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

  races: Array<any> = [];
  myRaces:Race[];
  race:any[];
  selectedTabIndex: number;
  user;

  private raceStateSubscription:Subscription;
  private userStateSubscription:Subscription;

  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,private raceService:RaceService) {
    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
      }
    });
    this.raceStateSubscription = this.store.select('raceState').subscribe(raceState => {
        this.races=raceState.races;
        console.log(this.races);
      });
  }
//https://stackoverflow.com/questions/46902829/how-can-i-load-components-added-in-tab-using-angular4

  ngOnInit() {
    this.selectedTabIndex = 0;
    this.raceService.getRaces().subscribe((res:any) =>{
      if (this.races.length<res.length){
        this.store.dispatch(new raceActions.EmptyActionRace([]));
        this.store.dispatch(new raceActions.AddListActionRace(res));
      }
    });
  }
  onOpenMenu(id:any): void {
    console.log(id);
  }

  OpenTab(){
  }
  onOpenMenuView(idRace:any){
     let id = idRace ? idRace : null;
  this.router.navigate(['/races/',id]);
  }

  onSelectChange(event) {
    this.selectedTabIndex = event.index;
    if (event.index == 1) {
      this.raceService.getMyRace(this.user.id).subscribe((response)=>{
        console.log(response);
      });
      console.log('Tab1 is selected!');
    }
  }
    ngOnDestroy(){
      this.store.dispatch(new raceActions.EmptyActionRace([]));
    }

}
