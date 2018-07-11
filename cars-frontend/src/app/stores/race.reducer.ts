import * as  raceActions from './race.action'
import {Race} from "../models/race";


export interface RaceState {
  race:Race,
  races :Race[],
}

const initialState: RaceState ={
  race:null,
  races:[],
}



export function raceReducer(state: any  = initialState, action: raceActions.RaceActions) {

  switch (action.type) {

    case raceActions.RACE_ADD:
      return {...state, races: [...state.races, action.payload]};

    case raceActions.RACES_EMPTY:
      return Object.assign({}, state, {reces:[]});

    case raceActions.RACES_ADD:
      return {...state, races: [...state.races, ...action.payload]};

    case raceActions.RACES_UPDATE:
      const car = state.races[action.payload.indexRace];
      const updatedCar = {
        ...car,
        ...action.payload.race

      };
      const races = [...state.races];
      races[action.payload.indexRace]= updatedCar;
      return {...state, races:races
      };

    case raceActions.RACES_DELETE:
      const oldRaces = [...state.cars];
      oldRaces.splice(action.payload, 1);
      return {...state, cars:oldRaces
      };

    default:
      return state;
  }


}
