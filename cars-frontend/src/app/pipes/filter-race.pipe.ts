import {Injectable, Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'filterRace'
})
//@Injectable()
export class FilterRacePipe implements PipeTransform {
  // input is an array of any
  // mod is the modulo, every mod items, create a row
  transform(input: any[], mod: any): any[][] {


    console.log("input..."+input+ "mod"+mod);
    return input.reduce((previous, next, index) => {

      console.log()
      if (index % mod === 0)
        previous.push([next]);
      else
        previous[previous.length - 1].push(next);
      return previous;
    }, <any[][]>[]);
  }
/*  transform(races: any[], term): any {
    console.log('term', term);

    console.log(races);
    for (let indexRaces in races) {
      console.log(indexRaces);
      console.log(races[indexRaces].userIds.length);
      for (var _i = 0; _i < races[indexRaces].userIds.length; _i++) {
        if (races[indexRaces].userIds[_i].id == term.id) {
          console.log("encontrado" + races[indexRaces].userIds[_i].id);
          return true
        }
        return false
      }
    }
  }*/
}


