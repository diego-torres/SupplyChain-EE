import { Component, OnInit } from '@angular/core';
import { FormGroup, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthenticationService } from './shared';

import 'style-loader!./login.scss';

@Component({
  selector: 'login',
  templateUrl: './login.html',
})
export class Login implements OnInit {
  public form: FormGroup;
  public email: AbstractControl;
  public password: AbstractControl;
  public submitted: boolean = false;
  error = '';

  constructor(fb: FormBuilder, private router: Router, private authenticationService: AuthenticationService) {
    this.form = fb.group({
      'email': ['', Validators.compose([Validators.required, Validators.minLength(4)])],
      'password': ['', Validators.compose([Validators.required, Validators.minLength(4)])]
    });

    this.email = this.form.controls['email'];
    this.password = this.form.controls['password'];
  }

  ngOnInit() {
    this.authenticationService.logout();
  }

  public onSubmit(values:  Object): void {
    this.submitted = true;
    if (this.form.valid) {
      this.authenticationService.login(values['email'], values['password'])
      .subscribe(result => {
        if (result === true) {
          this.router.navigate(['/home']);
        } else {
          this.error = 'Username or password are incorrect';
        }
      });
    }
  }
}
