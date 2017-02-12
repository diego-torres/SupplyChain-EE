import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Country } from '../country/country';
import { CountryService } from '../country/country.service';

import { State } from '../state/state';
import { StateService } from '../state/state.service';

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
        // TODO: Load country/state values for existing address.
        this.countryService.getAllCountries().then(data => { this.countries = data; });
    }

    onChangeCountry(selectedCountryId) {
        // TODO: Lock and show "loading" graphics for state dropdown, 
        // TODO: unlock and hide loading after service data transfer.
        this.stateService.getStatesByCountryId(selectedCountryId).then(states => { this.states = states; });
        (<FormControl>this.addressForm.controls['stateId']).setValue(0, { onlySelf: true });
    }
}
