import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {SelectionModel} from "@angular/cdk/collections";
import {Car} from "../../models/car";
import {Store} from "@ngrx/store";
import {Router} from "@angular/router";
import {AppStore} from "../../app.store";
import {UserService} from "../../services/user.service";
import {Subscription} from "../../../../node_modules/rxjs";
import * as carActions from "../../stores/car.action";
import {CarService} from "../../services/car.service";
import {RaceService} from "../../services/race.service";

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent  {
  cars: Car[] = [{brand:"",color:"",power: null}];
  displayedColumns = ['select', 'brand', 'color','power'];
  data = Object.assign(this.cars);
  dataSource = new MatTableDataSource<Element>(this.data);
  selection = new SelectionModel<Element>(true, []);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  private carStateSubscription:Subscription;
  private userStateSubscription: Subscription;
  constructor(private userService:UserService,private router : Router, private store:Store<AppStore>, private carService:CarService) {

      this.userStateSubscription = this.store.select('userState').subscribe(userState => {
        if (!userState.user) {
          this.router.navigate(["login"]);
        }
      });
    this.carStateSubscription = this.store.select('carState').subscribe(carState => {
        this.cars = carState.cars;
        this.data = Object.assign(this.cars);
        this.dataSource = new MatTableDataSource<Element>(this.data);
        console.log(carState.cars);
      //this.router.navigate(["login"]);
    });
  }
  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }
  removeSelectedRows() {
    this.selection.selected.forEach(item => {

      var idItem = item.id;
       idItem=""+idItem;
       console.log(idItem);
       this.carService.deleteCar(idItem).subscribe(response=>{
         console.log(response);
       });

      let index: number = this.data.findIndex(d => d === item);
      console.log(this.data.findIndex(d => d === item));

     // this.dataSource.data.splice(index,1);
      this.store.dispatch(new carActions.DeleteAction(index));
      //this.dataSource = new MatTableDataSource<Element>(this.dataSource.data);
    });
    this.selection = new SelectionModel<Element>(true, []);
  }
  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }
}
const ELEMENT_DATA: Car[] = [
];
