import {Component, OnInit, ViewChildren} from '@angular/core';
import {Store} from "@ngrx/store";
import {User} from "../../models/user";
import {Car} from "../../models/car";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {UserService} from "../../services/user.service";
import {Subscription} from "../../../../node_modules/rxjs";
import {CarService} from "../../services/car.service";
import {RaceService} from "../../services/race.service";
import {Race} from "../../models/race";

@Component({
  selector: 'app-races',
  templateUrl: './races.component.html',
  styleUrls: ['./races.component.css']
})
export class RacesComponent implements OnInit {

  users:User[];
  cars: Car[];
  races: any;
  selectedTabIndex: number;


  private userStateSubscription:Subscription;

  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,private racesService:RaceService) {


 /*   this.userStateSubscription = this.store.select('userState').subscribe(userState => {

      if (userState.users) {
        console.log(userState.users);
        this.users = userState.users;
        console.log(this.users);
      }
      //this.router.navigate(["login"]);
    });
*/
  }
//https://stackoverflow.com/questions/46902829/how-can-i-load-components-added-in-tab-using-angular4

  ngOnInit() {
    this.selectedTabIndex = 0;
    this.racesService.getRaces().subscribe(res =>{
      this.races=res;
    });
  }
}
