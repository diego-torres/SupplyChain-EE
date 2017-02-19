import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { Part } from './part';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class PartService {
  private partsUrl = 'http://localhost:8080/rest/partNumber';
  constructor(private http: Http){}

  addPartNumber(partNumber: Part): Observable<Part> {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({ headers: headers });

    return this.http.post(this.partsUrl, partNumber, options)
    .map((r: Response) => r.json())
    .catch((e: any) => Observable.throw(e.json().error || 'Server Error'));
  }

  getAllParts(): Observable<Part[]> {
    return this.http.get(this.partsUrl)
    .map((r: Response) => {
      let parts: Part[] = r.json().data;
      return parts;
    })
    .catch((e: any) => Observable.throw(e.json().error || 'server error'));
  }
}
