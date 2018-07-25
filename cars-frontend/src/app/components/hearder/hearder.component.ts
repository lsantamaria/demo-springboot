import {Component, OnInit, Input, ViewChild, ElementRef} from '@angular/core';
import {User} from "../../models/user";
import {AppStore} from "../../app.store";
import {Subscription} from "rxjs";
import {Store} from "@ngrx/store";
import * as raceActions from "../../stores/race.action";
import * as carActions from "../../stores/car.action";

import {SharedService} from "../../services/shared.service";

import * as loginActions from '../../stores/user.action'
import {Router} from "@angular/router";

@Component({
  selector: 'app-hearder',
  templateUrl: './hearder.component.html',
  styleUrls: ['./hearder.component.css']
})
export class HearderComponent implements OnInit {
  private userStateSubscription: Subscription;
  user: User;
  u = false;

  @ViewChild('myDiv') myButton: ElementRef;

  constructor(private store: Store<AppStore>, private sharedService: SharedService, private router: Router) {

    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
        this.user = userState.user;
      }
      this.router.navigate(["login"]);
    });
  }

  logout(): void {
    localStorage.removeItem(this.user.email);
    this.store.dispatch(new loginActions.LogoutAction(null));
    this.store.dispatch(new raceActions.EmptyActionRace([]));
    this.store.dispatch(new carActions.EmptyAction([]));
    this.user = null;
    this.router.navigate(["login"]);
  }

  ngOnInit() {
  }


}
