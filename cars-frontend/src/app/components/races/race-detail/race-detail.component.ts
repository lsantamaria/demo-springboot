import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {RaceService} from "../../../services/race.service";
import * as raceActions from "../../../stores/race.action";
import {Store} from "@ngrx/store";
import {AppStore} from "../../../app.store";

@Component({
  selector: 'app-race-detail',
  templateUrl: './race-detail.component.html',
  styleUrls: ['./race-detail.component.css']
})
export class RaceDetailComponent implements OnInit {

  detail_id;
  race;
  constructor(private route: ActivatedRoute, private raceService:RaceService, private store:Store<AppStore>) {

    this.route.params.forEach((params: Params) => {
      this.detail_id = JSON.parse((params['id']));
      this.raceService.getRace(this.detail_id).subscribe((response:any) => {
        this.race=response;
      });
    });
  }
  ngOnInit() {
  }
}
