import {CommonModule} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule,Routes} from  '@angular/router';
import {StoreModule} from "@ngrx/store";



import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatStepperModule,
  MatNativeDateModule,
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import 'hammerjs' ;

import { AppComponent } from './app.component';
import {NgModule} from "@angular/core";
import { LoginComponent } from './components/login/login.component';
import { AboutComponent } from './components/about/about.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HearderComponent } from './components/hearder/hearder.component';
import {UserService} from "./services/user.service";
import {RaceService} from "./services/race.service";
import {CarService} from "./services/car.service";
import { UsersComponent } from './components/users/users.component';
import { CarsComponent } from './components/cars/cars.component';
import { RacesComponent } from './components/races/races.component';
import {reducers} from "./app.store";
import {SharedService} from "./services/shared.service";



const appRoutes: Routes = [
  {path : '', component : LoginComponent},
  { path: 'users', component: UsersComponent },
  { path: 'login', component: LoginComponent },
  { path: 'races', component: RacesComponent },
  { path: 'cars', component: CarsComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AboutComponent,
    LandingPageComponent,
    HearderComponent,
    UsersComponent,
    CarsComponent,
    RacesComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatTableModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSliderModule,
    MatSnackBarModule,
    MatSortModule,
    MatStepperModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatNativeDateModule,
    StoreModule.forRoot(reducers),
  ],
  exports:[MatButtonModule,MatToolbarModule,MatListModule,MatTableModule],
  providers: [UserService,CarService,RaceService, SharedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
