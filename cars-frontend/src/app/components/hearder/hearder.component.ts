import {Component, OnInit, Input, ViewChild, ElementRef} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {AppStore} from "../../app.store";
import {Subscription} from "rxjs";
import {Store} from "@ngrx/store";
//import {LogOutUserAction} from "../../stores/user.reducer";
import {SharedService} from "../../services/shared.service";

import * as loginActions from '../../stores/user.action'
import {Router} from "@angular/router";

@Component({
  selector: 'app-hearder',
  templateUrl: './hearder.component.html',
  styleUrls: ['./hearder.component.css']
})
export class HearderComponent implements OnInit {
  private userStateSubscription:Subscription;
  user:User;
  u=false;

  @ViewChild('myDiv') myButton: ElementRef;




  constructor(private store:Store<AppStore>, private sharedService:SharedService,private router : Router) {

    this.userStateSubscription = this.store.select('userState').subscribe(userState => {

      if (userState.user) {
        console.log(userState.user);
        this.user=userState.user;
      }
      //this.router.navigate(["login"]);
    });

    this.sharedService.triggerParentMethod.subscribe( value =>{
      if(value == true){
        // Call some method here or some piece of code
        console.log('called from dialog');
      }
    });
  }
 // Emit  triggerParentMethod from your dialog:

//  this.sharedService.triggerParentMethod.next(true);


  logout(): void{
    this.store.dispatch(new loginActions.LogoutAction(this.user=null));
    this.router.navigate(["login"]);
  }
  ngOnInit() {
  }


}
