import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
    public token: string;

    constructor(private http: Http) {
        // set token if saved in local storage
        let loggedInUser = JSON.parse(localStorage.getItem('logedInUser'));
        this.token = loggedInUser && loggedInUser.token;
    }

    login(username: string, password: string): Observable<boolean> {
        let headers = new Headers({'Content-Type': 'application/json; charset=UTF-8'});
        let options = new RequestOptions({ headers: headers});
        return this.http.post(
            'http://localhost:8080/rest/authenticate',
            JSON.stringify({ userName: username, password: password }), options)
        .map((response: Response) => {
            let token = response.json() && response.json().token;
            if (token) {
                this.token = token;
                localStorage.setItem('loggedInUser', JSON.stringify({ username: username, token: token }));
                return true;
            } else {
                console.log('MISSING TOKEN!');
                return false;
            }

        });
    }

    logout(): void {
        this.token = null;
        localStorage.removeItem('loggedInUser');
    }
}
