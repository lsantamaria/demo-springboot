import {User} from  "../models/user";

import * as  carActions from './car.action'
import {Car} from "../models/car";


export interface CarState {
  car:Car,
  cars :Car[],
}

const initialState: CarState ={
  car:null,
  cars:[{brand:'honda', color:'azul',  power:23},{brand:'citroen', color:'azul',  power:300},{brand:'bmw', color:'gris',  power:4000},{brand:'toyota', color:'negro',  power:235},{brand:'mercedes', color:'gris', power:323},{brand:'mazda', color:'rojo', power:236},{brand:'ola', color:'lala',  power:23},{brand:'ola', color:'lala', power:23}],
}



export function carReducer(state: any  = initialState, action: carActions.CarActions) {

  switch (action.type) {

    case carActions.CAR_ADD:
      return {...state, cars: [...state.cars, action.payload]};

    case carActions.CARS_ADD:
      return {...state, cars: [...state.cars, ...action.payload]};

    case carActions.CARS_UPDATE:
      const car = state.cars[action.payload.indexCar];
      const updatedCar = {
        ...car,
        ...action.payload.car

      };
      const cars = [...state.cars];
      cars[action.payload.indexCar]= updatedCar;
      return {...state, cars:cars
      };

    case carActions.CARS_DELETE:
      const oldCars = [...state.cars];
      oldCars.splice(action.payload, 1);
      return {...state, cars:oldCars
      };

    default:
      return state;
  }


}
