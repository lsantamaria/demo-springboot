import  {Action } from  '@ngrx/store';
import {User} from "../models/user";

export const USER_LOGIN_SUCCESS = 'USER_LOGIN_SUCCESS';
export const USER_LOGOUT ='USER_LOGOUT';
export const USER_ADD ='USER_ADD';

export class LoginAction implements Action {
   type=USER_LOGIN_SUCCESS;
  constructor(public user:User){
  }
}

export class LogoutAction implements Action {
  type=USER_LOGOUT;
  constructor(public user:User){
  }
}

export class AddAction implements Action {
  type=USER_ADD;
  user: User
}
export  type LoginActions = LoginAction | LogoutAction | AddAction ;



