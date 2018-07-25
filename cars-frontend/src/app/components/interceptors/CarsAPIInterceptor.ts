import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/observable';
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {AppStore} from "../../app.store";
import {Subscription} from "rxjs/Subscription";

@Injectable()
export class CarsAPIInterceptor implements HttpInterceptor {

  private userStateSubscription: Subscription;
  userState;

  constructor(private store: Store<AppStore>, private router: Router,) {
    this.userStateSubscription = this.store.select('userState').subscribe(userState => {
      this.userState = userState;
    });
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (req.url.includes("/jpa/")) {
      //TODO:extract it to a service
      let authToken = localStorage.getItem(this.userState.user.email);
      if(authToken != null){
        console.log("Getting authToken... " + authToken);
        const authReq = req.clone({
          headers: req.headers.set('Authorization', authToken)
        });
        return next.handle(authReq);
      }
      else{
        console.log("No auth token. Navigating to login page")
        this.router.navigate(["login"]);
      }
    }
    return next.handle(req);
  }
}
