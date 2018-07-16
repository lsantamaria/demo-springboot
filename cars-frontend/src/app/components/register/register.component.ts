import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {AppStore} from "../../app.store";
import * as carActions from "../../stores/car.action";
import * as loginActions from "../../stores/user.action";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User;
  email : string
  password : string
  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>,) {
  }
  register() : void {
    this.user= new User();
    if(this.email  && this.password !=null ){
      this.user.email= this.email;
      this.user.cars=[];
      this.user.password=this.password;
      this.userService.login(this.user).subscribe((response:any)=>{
        if(!response){
          alert("Invalid format, only letters are allowed");
        }
        this.user=response;
        this.store.dispatch(new loginActions.LoginAction(this.user));
        this.store.dispatch(new carActions.AddListAction(this.user.cars));
        this.router.navigate(["profile"]);
      });
    }else {alert("It is necessary to fill all the fields and only letters are allowed");
    }
  }
  ngOnInit() {
  }
}
