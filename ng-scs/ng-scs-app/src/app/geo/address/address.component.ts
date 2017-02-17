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
    public selectedCountry: Country;

    constructor(private countryService: CountryService, private stateService: StateService) { }

    ngOnInit() {
        this.countryService.getAllCountries().then(
            data => {
                this.countries = data;
                if (this.addressForm && (<FormControl>this.addressForm.controls['countryId']).value > 0) {
                    this.stateService.getStatesByCountryId(
                        (<FormControl>this.addressForm.controls['countryId']).value)
                        .then(states => { this.states = states; });
                }
            });
    }

    onChangeCountry(selectedCountryId) {
        (<FormControl>this.addressForm.controls['stateId']).disable();
        this.stateService.getStatesByCountryId(selectedCountryId)
        .then(states => {
            this.states = states;
            (<FormControl>this.addressForm.controls['stateId']).enable();
            (<FormControl>this.addressForm.controls['stateId']).setValue(0, { onlySelf: true });
        });
    }
}
