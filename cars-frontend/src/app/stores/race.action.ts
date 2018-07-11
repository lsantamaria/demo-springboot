import {Action} from "@ngrx/store";


export const RACE_ADD ='RACE_ADD';
export const RACES_ADD ='RACES_ADD';
export const RACES_UPDATE ='RACES_UPDATE';
export const RACES_DELETE ='RACES_DELETE';
export const RACES_EMPTY ='RACES_EMPTY';


export class AddActionRace implements Action {
  type=RACE_ADD;

  constructor(public payload: any){}
}
export class AddListActionRace implements Action {
  type=RACES_ADD;
  constructor(public payload: any[]){}
}

export class UpdateActionRace implements Action {
  type=RACES_UPDATE;
  constructor(public payload:{indexRace:any, race:any} ){}
}

export class DeleteActionRace implements Action {
  type=RACES_DELETE;
  constructor(public payload:any ){}
}
export class EmptyActionRace implements Action {
  type=RACES_EMPTY;
  constructor(public payload: any[]){}
}

export  type RaceActions =  AddActionRace | AddListActionRace  | DeleteActionRace | UpdateActionRace |EmptyActionRace;

