import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {Store} from "@ngrx/store";
import * as loginActions from '../../stores/user.action'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user:User;
  username : string
  password : string
  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,) {

  }
  login() : void {
    this.user= new User();
    if(this.username == 'admin' && this.password == 'admin'){
      this.user.name= this.username;
      this.user.cars=[];
      console.log(this.user);
      this.store.dispatch(new loginActions.LoginAction(this.user));
      this.router.navigate(["users"]);
    }else {
      alert("Invalid credentials");
    }
  }

  ngOnInit() {
  }
}
