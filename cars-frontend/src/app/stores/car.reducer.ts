import * as  carActions from './car.action'
import {Car} from "../models/car";


export interface CarState {
  car:Car,
  cars :Car[],
}

const initialState: CarState ={
  car:null,
  cars:[],
}



export function carReducer(state: any  = initialState, action: carActions.CarActions) {

  switch (action.type) {

    case carActions.CAR_ADD:
    return {...state, cars: [...state.cars, action.payload]};

    case carActions.CARS_EMPTY:
      return initialState;

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
