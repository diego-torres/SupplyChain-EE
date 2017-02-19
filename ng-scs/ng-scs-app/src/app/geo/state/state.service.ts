import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { State } from './state';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class StateService {
  private statesUrl = 'http://localhost:8080/rest/geo/state';
  constructor(private http: Http){}

  getStatesByCountryId(id: number): Observable<State[]> {
    let urlById = this.statesUrl + '/country/' + id;
    return this.http.get(urlById)
    .map((r: Response) => r.json().data)
    .catch((e: any) => Observable.throw(e.json().error || 'server error'));
  }

  getStateById(id: number): Observable<State> {
    let urlById = this.statesUrl + '/' + id;
    return this.http.get(urlById)
    .map((r: Response) => r.json().data.pop())
    .catch((e: any) => Observable.throw(e.json().error || 'server error'));
  }
}
