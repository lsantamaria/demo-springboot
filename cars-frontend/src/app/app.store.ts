import {userReducer, UserState} from "./stores/user.reducer";
import {ActionReducerMap} from "@ngrx/store"
import {carReducer, CarState} from "./stores/car.reducer";
import {raceReducer, RaceState} from "./stores/race.reducer";

export  interface AppStore {
  userState: UserState,
  carState:CarState,
  raceState:RaceState
}

export const reducers: ActionReducerMap<AppStore> = {
  userState:userReducer,
  carState:carReducer,
  raceState:raceReducer
};
