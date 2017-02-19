import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Country } from '../country/country';
import { CountryService } from '../country/country.service';
import { State } from '../state/state';
import { StateService } from '../state/state.service';
import { Address } from './address';

@Component({
    selector: 'address',
    templateUrl: './address-form.html'
})
export class AddressComponent {
  @Input('group')
  public addressForm: FormGroup;

  public countries: Country[] = [];
  public states: State[] = [];

  constructor(
    private countryService: CountryService,
    private stateService: StateService) { }

  ngOnInit() {
    this.countryService.getAllCountries()
    .subscribe(
      countries => {
        this.countries = countries;
        if (this.addressForm && (<FormControl>this.addressForm.controls['countryId']).value > 0) {
          let selectedCountryId: number = (<FormControl>this.addressForm.controls['countryId']).value;
          this.stateService.getStatesByCountryId(selectedCountryId)
          .subscribe(
            states => {
              this.states = states;
            },
            e => console.log(e));
        }
      },
      err => { console.log(err); }
    );
  }

  onChangeCountry(selectedCountryId: number) {
    this.stateService.getStatesByCountryId(selectedCountryId)
    .subscribe(
      states => {
        this.states = states;
        (<FormControl>this.addressForm.controls['stateId']).setValue(0, { onlySelf: true });
      },
      e => console.log(e));
  }
}
