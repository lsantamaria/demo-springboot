import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {AppStore} from "../../app.store";
import * as carActions from "../../stores/car.action";
import * as loginActions from "../../stores/user.action";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  email: string;
  name: string;
  password: string;

  constructor(private userService: UserService, private router: Router, private store: Store<AppStore>,) {
  }

  register(): void {
    this.user = new User();
    if (this.email != null && this.password != null && this.name != null) {
      this.user.email = this.email;
      this.user.cars = [];
      this.user.password = this.password;
      this.user.name = this.name;
      this.userService.register(this.user).subscribe(
        (response: User) => {
          if (!response) {
            alert("Error registering user. Please try it again");
          }
          this.user = response;
          this.store.dispatch(new loginActions.LoginAction(this.user));
          this.store.dispatch(new carActions.AddListAction(this.user.cars));
          this.router.navigate(["profile"]);
        },
        (error: HttpErrorResponse) => {
          console.log("ERROR response:");
          console.log(error);
          if (error.status != 200) {
            alert("Error registering user . Please try it again");
          }
        }
      );
    } else {
      alert("It is necessary to fill all the fields and only letters are allowed");
    }
  }

  ngOnInit() {
  }
}
