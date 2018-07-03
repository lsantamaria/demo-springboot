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
  users:[{name:'marcelus', cars:[{brand:'honda', color:'azul',  power:23},{brand:'citroen', color:'azul',  power:300},{brand:'bmw', color:'gris',  power:4000}]},{name:'efren', cars:[{brand:'toyota', color:'negro',  power:235}]},{name:'lluis', cars:[{brand:'mercedes', color:'gris', power:323}]},{name:'ivan', cars:[{brand:'mazda', color:'rojo', power:236}]},{name:'marcelus', cars:[{brand:'ola', color:'lala',  power:23}]},{name:'marcelus', cars:[{brand:'ola', color:'lala', power:23}]} ],
}



export function userReducer(state: any  = initialState, action: loginActions.LoginActions) {

  switch (action.type) {

    case loginActions.USER_LOGIN_SUCCESS:
      return {...state, user: action.user, loading:  false};


    case loginActions.USER_LOGOUT:
      return {...state, user: action.user, loading:  false};


    case loginActions.USER_ADD:
      return {...state, users: [...state.users, action.user]};


    default:
      return state;
  }


}
