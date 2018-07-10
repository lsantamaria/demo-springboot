import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {RaceService} from "../../../services/race.service";

@Component({
  selector: 'app-race-detail',
  templateUrl: './race-detail.component.html',
  styleUrls: ['./race-detail.component.css']
})
export class RaceDetailComponent implements OnInit {

  detail_id;
  users;
  constructor(private route: ActivatedRoute, private raceService:RaceService) {

    this.route.params.forEach((params: Params) => {
      this.detail_id = JSON.parse((params['id']));
      this.raceService.getRace(this.detail_id).subscribe(response => {
        this.users=response.users;
        console.log(response);
      });
    });
  }

  ngOnInit() {
  }
}