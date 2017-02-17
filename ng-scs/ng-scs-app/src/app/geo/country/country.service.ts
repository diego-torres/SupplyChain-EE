import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { Country } from './country';

// RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CountryService {
  private countriesUrl = 'http://localhost:8080/rest/geo/country';
  constructor(private http: Http){}

  // https://scotch.io/tutorials/angular-2-http-requests-with-observables
  getAllCountries(): Observable<Country[]> {
    return this.http.get(this.countriesUrl)
      .map((result: Response) => result.json().data)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  // Simulate GET /countries/:id
  getCountryById(id: number): Observable<Country> {
    let urlById = this.countriesUrl + '/' + id;
    return this.http.get(urlById)
      .map((result: Response) => result.json().data)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
