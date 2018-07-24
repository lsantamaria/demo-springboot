import {Component, OnInit, ViewChildren} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {Car} from "../../models/car";
import {Store} from "@ngrx/store";
import {AppStore} from "../../app.store";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  user:User;
  cars: Car[];

  @ViewChildren('tooltip') tooltips;
  show;
  private userStateSubscription:Subscription;

  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>) {

    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
        this.user = userState.user;
      }else
        this.router.navigate(["login"]);
    });
  }

  showAllTooltips() {
    this.show = !this.show;
    if (this.show) {
      setTimeout(() => {
        this.tooltips._results.forEach(item => item.show());
      }, 5)
    } else {
      this.tooltips._results.forEach(item => item.hide());
    }
  }
  ngOnInit() {
  //    this.reloadData()
  }
/*
  reloadData() {
    this.users = this.userService.getUsersList();
    console.log(this.users);
  }
*/

}
