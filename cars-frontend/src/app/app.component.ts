import {Component, ViewChildren} from '@angular/core';
import {Router} from "@angular/router";
import {Car} from "./models/car";
import {Store} from "@ngrx/store";
import {UserService} from "./services/user.service";
import {User} from "./models/user";
import {Subscription} from "../../node_modules/rxjs";
import {AppStore} from "./app.store";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user: User;
  private userStateSubscription: Subscription;

  constructor(private userService: UserService, private router: Router, private store: Store<AppStore>) {

    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
        this.user = userState.user;
        this.router.navigate(["cars"]);
      } else
        this.router.navigate(["login"]);
    });
  }

}
