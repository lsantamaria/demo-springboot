import {Component, OnInit, Input} from '@angular/core';
import {User} from "../../models/user";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {


  @Input() user: User;
  constructor() { }

  ngOnInit() {
  }

}
