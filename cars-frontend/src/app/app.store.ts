import {userReducer, UserState} from "./stores/user.reducer";
import {ActionReducerMap} from "@ngrx/store"
import {carReducer, CarState} from "./stores/car.reducer";

export  interface AppStore {
  userState: UserState,
  carState:CarState
}

export const reducers: ActionReducerMap<AppStore> = {
  userState:userReducer,
  carState:carReducer
};
