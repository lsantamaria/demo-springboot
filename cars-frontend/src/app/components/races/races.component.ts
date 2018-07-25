import {Component, OnInit, OnDestroy} from '@angular/core';
import {Store} from "@ngrx/store";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs/Subscription";
import {RaceService} from "../../services/race.service";
import {Race} from "../../models/race";
import * as raceActions from "../../stores/race.action";

@Component({
  selector: 'app-races',
  templateUrl: './races.component.html',
  styleUrls: ['./races.component.css']
})
export class RacesComponent implements OnInit, OnDestroy {

  races: Array<any> = [];
  myRaces: Race[];
  race: any[];
  selectedTabIndex: number;
  user;
  filterargs;

  private raceStateSubscription: Subscription;
  private userStateSubscription: Subscription;

  constructor(private userService: UserService, private router: Router, private store: Store<AppStore>, private raceService: RaceService) {
    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      if (userState.user) {
        this.user = userState.user;
        this.filterargs = {id: this.user.id};
      } else
        this.router.navigate(["login"]);
    });
    this.raceStateSubscription = this.store.select('raceState').subscribe((raceState: any) => {
      this.races = raceState.races;
    });

  }

  ngOnInit() {
    this.selectedTabIndex = 0;
    this.raceService.getRaces().subscribe((res: any) => {
      if (this.races.length < res.length) {
        this.store.dispatch(new raceActions.EmptyActionRace([]));
        this.store.dispatch(new raceActions.AddListActionRace(res));
      }
    });
  }

  onOpenMenu(id: any): void {
  }

  OpenTab() {
  }

  onOpenMenuView(idRace: any) {
    let id = idRace ? idRace : null;
    this.router.navigate(['/races/', id]);
  }

  onOpenMenuJoin(object) {
    let idpath = "" + object.id_race;
    let updatedRace = this.races[object.position];
    updatedRace.userIds.push(this.user.id);
    let object_id = {
      idRace: idpath,
      idUser: this.user.id
    };
    this.raceService.addUserToRace(object_id).subscribe(response => {
      this.store.dispatch(new raceActions.UpdateActionRace({
        indexRace: object.position,
        race: updatedRace
      }));
    });
  }

  onOpenMenuDelete(object) {
    let idpath = "" + object.id_race;
    this.raceService.deleteRace(idpath).subscribe(response => {
      this.store.dispatch(new raceActions.DeleteActionRace(object.position));
    });
  }

  onSelectChange(event) {
    this.selectedTabIndex = event.index;
    if (event.index == 1) {
      this.raceService.getMyRace(this.user.id).subscribe((response: any) => {
        this.store.dispatch(new raceActions.EmptyActionRace([]));
        this.store.dispatch(new raceActions.AddListActionRace(response));
      });
    }
  }

  ngOnDestroy() {
    this.store.dispatch(new raceActions.EmptyActionRace([]));
  }

  indexTracker(index: number, value: any) {
    return index;
  }
}
