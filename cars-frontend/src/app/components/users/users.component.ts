import {Component, OnInit, ViewChildren} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {Car} from "../../models/car";
import {Store} from "@ngrx/store";
import {AppStore} from "../../app.store";
import {Subscription} from "../../../../node_modules/rxjs";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users:User[];
  cars: Car[];

  //users:Observable<User[]>;


  @ViewChildren('tooltip') tooltips;

 // items = [{ comment: "Comment1" }, { comment: "Comment2" }, { comment: "Comment3" }]
  show;
  private userStateSubscription:Subscription;

  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>) {


    this.userStateSubscription = this.store.select('userState').subscribe(userState => {

      if (userState.users) {
        console.log(userState.users);
        this.users = userState.users;
        console.log(this.users);
      }
      //this.router.navigate(["login"]);
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
