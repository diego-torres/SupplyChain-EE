import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private router: Router){}

    canActivate() {
        if (localStorage.getItem('loggedInUser')) {
            console.log('loggedInUser: ' + localStorage.getItem('loggedInUser'));
            // logged in so return true
            return true;
        }

        console.log('Unauthorized, go to login.');
        this.router.navigate(['/login']);
        return false;
    }
}
