import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Rx';

import { Company } from './company';
import { Address } from '../../../geo/address/address';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CompanyService {
  private companiesUrl = 'http://localhost:8080/rest/company';

  constructor(private http: Http){}

  // Simulate POST /companies
  addCompany(company: Company): Observable<Company> {
    let companyString = JSON.stringify(company);
    if (company.rolesArray)
      company.roles = company.rolesArray.join(', ');

    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({ headers: headers });

    return this.http.post(this.companiesUrl, company, options)
    .map((r: Response) => r.json())
    .catch((e: any) => Observable.throw(e.json().error || 'Server Error'));
  }

  deleteCompanyById(id: number): Observable<Company> {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({ headers: headers });

    return this.http.delete(this.companiesUrl + '/' + id)
    .map((r: Response) => r.json())
    .catch((e: any) => Observable.throw(e.json().error || 'Server Error'));
  }

  // Simulate PUT /companies/:id
  updateCompanyById(id: number, values: Object = {}): Observable<Company> {
    let company: Company = new Company(values);

    if (company.rolesArray)
      company.roles = company.rolesArray.join(', ');

    let companyString = JSON.stringify(company);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({ headers: headers });

    return this.http.put(this.companiesUrl, company, options)
    .map((r: Response) => r.json())
    .catch((e: any) => Observable.throw(e.json().error || 'Server Error'));
  }

  // Simulate GET /companies
  getAllCompanies(): Observable<Company[]> {
    return this.http.get(this.companiesUrl)
      .map(
        (result: Response) => {
          let companies: Company[] = result.json().data;
          companies.forEach( c => { c.rolesArray = c.roles.split(', '); });
          return companies;
        })
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  // Simulate GET /companies/:id
  getCompanyById(id: number): Observable<Company> {
    return this.http.get(this.companiesUrl + '/' + id)
      .map((result: Response) => {
        let company: Company = result.json().data.pop();
        company.rolesArray = company.roles.split(', ');
        return company;
      })
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
