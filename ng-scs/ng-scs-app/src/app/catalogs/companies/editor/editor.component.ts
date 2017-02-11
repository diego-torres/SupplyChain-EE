import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';

import { Company } from '../shared/company';
import { CompanyService } from '../shared/company.service';

@Component({
    selector: 'company-editor-form',
    templateUrl: './editor-form.html'
})
export class CompanyEditorForm implements OnInit, OnDestroy {
    public id: number;
    public submitted: boolean;
    public fgClient: FormGroup;
    public events: any[] = [];

    private sub: any;
    constructor(
        private route: ActivatedRoute,
        private _fb: FormBuilder,
        private companyService: CompanyService,
        private router: Router
    ) { }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            this.id = +params['id'];
        });

        /*
        (<FormControl>this.fgClient.controls['name']).setValue('Nowgroup', { onlySelf: true });
        */

        this.fgClient = new FormGroup({
            name: new FormControl('', [
                <any>Validators.required,
                <any>Validators.minLength(5),
                <any>Validators.maxLength(150)]),
            taxId: new FormControl('', [
                <any>Validators.minLength(9),
                <any>Validators.maxLength(30)]),
            email: new FormControl('', [
                <any>Validators.required,
                <any>Validators.minLength(5),
                <any>Validators.maxLength(150)]),
            buyer: new FormControl(false),
            seller: new FormControl(false),
            sender: new FormControl(false),
            receiver: new FormControl(false),
            freighter: new FormControl(false),
            trader: new FormControl(false),
            billable: new FormControl(false),
            addresses: this._fb.array([])
        });
    }

    initAddress() {
        return this._fb.group({
            addressType: ['', Validators.required],
            street: ['', Validators.required],
            additionalInfo: [''],
            country: ['', Validators.required],
            city: ['', Validators.required],
            state: ['', Validators.required],
            zip: ['', Validators.required],
            landLine: [''],
            contactName: ['']
        });
    }

    addAddress() {
        const control = <FormArray> this.fgClient.controls['addresses'];
        const addrCtrl = this.initAddress();

        control.push(addrCtrl);
    }

    removeAddress(i: number){
        const control = <FormArray>this.fgClient.controls['addresses'];
        console.log('removing: ' + i);
        control.removeAt(i);
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    save(model: Company, isValid: boolean) {
        console.log(model, isValid);
        if (isValid) {
            let clone: Company;
            clone.name = model.name;
            clone.taxId = model.taxId;
            clone.email = model.email;

            let _iRole: number = 0;
            if (model.buyer){
                if (_iRole === 0)
                    _iRole = 2;
            }

            if (model.seller) {
                if (_iRole > 0)
                    _iRole = _iRole * 3;
                else
                    _iRole = 3;
            }

            if (model.sender) {
                if (_iRole > 0)
                    _iRole = _iRole * 5;
                else
                    _iRole = 5;
            }

            if (model.receiver) {
                if (_iRole > 0)
                    _iRole = _iRole * 7;
                else
                    _iRole = 7;
            }

            if (model.freighter) {
                if (_iRole > 0)
                    _iRole = _iRole * 11;
                else
                    _iRole = 11;
            }

            if (model.trader) {
                if (_iRole > 0)
                    _iRole = _iRole * 13;
                else
                    _iRole = 13;
            }

            if (model.billable) {
                if (_iRole > 0)
                    _iRole = _iRole * 17;
                else
                    _iRole = 17;
            }
            clone.companyRole = _iRole;

            companyService.addCompany(clone);
            this.router.navigate('/catalogs/companies');
        }
    }

}
