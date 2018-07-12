import { Component, OnInit } from '@angular/core';
import {Car} from "../../../models/car";
import * as carActions from "../../../stores/car.action";
import {Store} from "@ngrx/store";
import {AppStore} from "../../../app.store";

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
  addMoreCars:Car[];

  constructor(private store:Store<AppStore>) { }

  ngOnInit() {
  }

  add(){
    this.car = new Car(this.brand,this.color,this.power);
    if(this.brand && this.color && this.power !=null){
      this.store.dispatch(new carActions.AddAction(this.car));
    }
    this.brand="";
    this.color="";
    this.power=null;

  }

  cancel(){
    this.brand="";
    this.color="";
    this.power=null;
  }
}
