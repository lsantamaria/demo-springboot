import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {Store} from "@ngrx/store";
import * as loginActions from '../../stores/user.action'
import * as carActions from "../../stores/car.action";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User;
  email : string;
  password : string;
  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,) {
  }
  login() : void {
    this.user= new User();
    if(this.email != null && this.password != null ){
      this.user.email= this.email;
      this.user.cars=[];
      this.user.password=this.password;
      this.userService.login(this.user).subscribe(
        (response) => {
          if(!response){
            alert("Error during login. Please try it again");
          }
          let authToken = response.headers.get("Authorization");
          localStorage.setItem(this.user.email, authToken);
          this.user = response.body;
          this.store.dispatch(new loginActions.LoginAction(this.user));
          this.store.dispatch(new carActions.AddListAction(this.user.cars));
          this.router.navigate(["cars"]);
        },
        (error: HttpErrorResponse) => {
          console.log("ERROR response:");
          console.log(error);
          alert("Error logging in user . Please try it again");

        }
      );

    }else {alert("It is necessary to fill all the fields and only letters are allowed");
    }
  }
  ngOnInit() {
  }
}
