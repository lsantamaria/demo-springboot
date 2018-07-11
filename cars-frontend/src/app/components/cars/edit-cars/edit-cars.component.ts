import { Component, OnInit } from '@angular/core';
import {Car} from "../../../models/car";
import * as carActions from "../../../stores/car.action";
import {Store} from "@ngrx/store";
import {AppStore} from "../../../app.store";
import {Subscription} from "../../../../../node_modules/rxjs";
import {User} from "../../../models/user";
import {CarService} from "../../../services/car.service";

@Component({
  selector: 'app-edit-cars',
  templateUrl: './edit-cars.component.html',
  styleUrls: ['./edit-cars.component.css']
})
export class EditCarsComponent implements OnInit {

  brand:string;
  color:string;
  power:number;
  car:Car;
  user;
  addMoreCars:Car[];
  private userStateSubscription:Subscription;

  constructor(private store:Store<AppStore>, private carService:CarService) {
    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
        console.log(userState.user);
        this.user = userState.user;
        console.log(this.user);
      }
    });
  }

  ngOnInit() {
  }

  add(){
    this.car = new Car();
    if(this.brand && this.color && this.power  ){

      let id = String(this.user.id);
      this.car.brand=this.brand;
      this.car.color=this.color;
      this.car.power=this.power;
      this.carService.addCar(id,this.car).subscribe((response:any)=>{
        console.log(response);
        this.store.dispatch(new carActions.AddAction(this.car));
      });

    }else {
      alert("It is necessary to fill all the fields or the field power is not a number");
    }
    this.brand = "";
    this.color = "";
    this.power = null;
  }
  cancel(){
    this.brand="";
    this.color="";
    this.power=null;
  }
}
