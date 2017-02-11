import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
    selector: 'address',
    templateUrl: './address-form.html'
})
export class AddressComponent {
    @Input('group')
    public addressForm: FormGroup;

    constructor() { }
}
