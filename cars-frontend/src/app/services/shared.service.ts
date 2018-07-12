import {Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class SharedService{
  public triggerParentMethod: Subject<boolean> = new Subject<boolean>();
}
