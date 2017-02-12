import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';

import { Company } from '../shared/company';
import { Address } from '../../../geo/address/address';
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

        if (this.id === 0) {
            this.initCompany();
        } else {
            let company: Company = this.companyService.getCompanyById(this.id);
            this.initCompany(company);
        }
    }

    initCompany(company?: Company) {
        if (company){
            // with values
            this.fgClient = new FormGroup({
                name: new FormControl(company.name, [
                    <any>Validators.required,
                    <any>Validators.minLength(5),
                    <any>Validators.maxLength(150)]),
                taxId: new FormControl(company.taxId),
                email: new FormControl(company.email),
                buyer: new FormControl(company.buyer),
                seller: new FormControl(company.seller),
                sender: new FormControl(company.sender),
                receiver: new FormControl(company.receiver),
                freighter: new FormControl(company.freighter),
                trader: new FormControl(company.trader),
                billable: new FormControl(company.billable),
                addresses: this._fb.array([])
            });
            company.addresses.forEach(address => { this.addAddress(address); });
        } else {
            // empty
            this.fgClient = new FormGroup({
                name: new FormControl('', [
                    <any>Validators.required,
                    <any>Validators.minLength(5),
                    <any>Validators.maxLength(150)]),
                taxId: new FormControl(''),
                email: new FormControl(''),
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
    }

    initAddress(address?: Address): FormGroup {
        console.log('Adding address control', address);
        if (address) {
            return this._fb.group({
                addressType: [address.addressType],
                streetAddress: [address.streetAddress],
                additionalAddressInfo: [address.additionalAddressInfo],
                city: [address.city],
                zip: [address.zip],
                landLine: [address.landLine],
                contactName: [address.contactName],
                countryId: [address.geoState && address.geoState.country ? address.geoState.country.id : 0 ],
                stateId: [address.geoState ? address.geoState.id : 0]
            });
        } else {
           return this._fb.group({
                addressType: [''],
                streetAddress: [''],
                additionalAddressInfo: [''],
                city: [''],
                zip: [''],
                landLine: [''],
                contactName: [''],
                countryId: [''],
                stateId: ['']
            });
        }
    }

    addAddress(address?: Address) {
        const control = <FormArray> this.fgClient.controls['addresses'];
        const addrCtrl = this.initAddress(address);

        control.push(addrCtrl);
    }

    removeAddress(i: number){
        const control = <FormArray>this.fgClient.controls['addresses'];
        control.removeAt(i);
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    save(model: any) {
        let company: Company = new Company(model);
        company.id = this.id;

        if (company.id === 0)
            this.companyService.addCompany(company);
        else
            this.companyService.updateCompanyById(company.id, company);

        this.router.navigate(['/catalogs/companies']);
    }

    cancel() {
        this.router.navigate(['/catalogs/companies']);
    }
}
