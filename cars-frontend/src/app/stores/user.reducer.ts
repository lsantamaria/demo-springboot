import {User} from  "../models/user";

import * as  loginActions from './user.action'
//import { LoginActions} from "./user.action";


export interface UserState {
  loading: boolean,
  error: boolean,
  user:User,
  users:User[]
}

const initialState: UserState ={
  loading:false,
  error:false,
  user:null,
  users:[],
}



export function userReducer(state: any  = initialState, action: loginActions.LoginActions) {

  switch (action.type) {

    case loginActions.USER_LOGIN_SUCCESS:
      return {...state, user: action.user, loading:  false};


    case loginActions.USER_LOGOUT:
      return initialState;

    case loginActions.USER_ADD:
      return {...state, users: [...state.users, action.user]};


    default:
      return state;
  }


}
