import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';

import { Company } from '../shared/company';
import { Address } from '../../../geo/address/address';
import { State } from '../../../geo/state/state';
import { CompanyService } from '../shared/company.service';
import { StateService } from '../../../geo/state/state.service';

import { Observable } from 'rxjs/Observable';

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
    private stateService: StateService,
    private router: Router
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    this.initCompany();
    if (this.id !== 0)
      // TODO: Block with "loading"
      this.companyService.getCompanyById(this.id)
      .subscribe(
        c => {
          console.log(c);
          this.initCompany(c);
        },
        e => {
          console.log(e);
        }
      );
  }

  initCompany(company?: Company) {
    if (company) {
      // with values
      this.fgClient = new FormGroup({
          name: new FormControl(company.name, [
              <any>Validators.required,
              <any>Validators.minLength(5),
              <any>Validators.maxLength(150)]),
          taxId: new FormControl(company.taxId),
          email: new FormControl(company.email),
          buyer: new FormControl(company.rolesArray && company.rolesArray.indexOf('buyer') !== -1),
          seller: new FormControl(company.rolesArray && company.rolesArray.indexOf('seller') !== -1),
          sender: new FormControl(company.rolesArray && company.rolesArray.indexOf('sender') !== -1),
          receiver: new FormControl(company.rolesArray && company.rolesArray.indexOf('receiver') !== -1),
          freighter: new FormControl(company.rolesArray && company.rolesArray.indexOf('freighter') !== -1),
          trader: new FormControl(company.rolesArray && company.rolesArray.indexOf('trader') !== -1),
          billable: new FormControl(company.rolesArray && company.rolesArray.indexOf('billable') !== -1),
          addresses: this._fb.array([])
      });
      if (company.addresses)
        company.addresses.forEach(address => { this.addAddress(address); });
    } else
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

  initAddress(address?: Address): FormGroup {
    if (address) {
      return this._fb.group({
        id: [address.id],
        addressType: [address.addressType],
        streetAddress: [address.streetAddress],
        additionalAddressInfo: [address.additionalAddressInfo],
        city: [address.city],
        zip: [address.zip],
        landLine: [address.landLine],
        contactName: [address.contactName],
        countryId: [address.geoState && address.geoState.country ? address.geoState.country.id : ''],
        stateId: [address.geoState ? address.geoState.id : 0]
      });
    } else
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

  save(model: any): void {
    let company: Company = new Company(model);
    console.log('saving company', company);
    company.id = this.id;
    let observables: Observable<State>[] = [];
    company.addresses.forEach(
      a => {
        observables.push(this.stateService.getStateById(a.stateId));
      }
    );

    let source = Observable.forkJoin(observables);
    source.subscribe(
      aStates => {
        company.addresses.forEach( (a, i) => {
          console.log('resolving state for address', a);
          let aState: State = aStates.filter(s => s.id === Number(a.stateId)).pop();
          if (aState)
            a.geoState = aState;
        });
      },
      err => { console.log(err); },
      () => {
        console.log('posting company', company);
        if (company.id === 0)
            this.companyService.addCompany(company)
            .subscribe(
              c => {
                this.router.navigate(['/catalogs/companies']);
              },
              e => { console.log(e); }
            );
        else
            this.companyService.updateCompanyById(company.id, company)
            .subscribe(
              c => {
                this.router.navigate(['/catalogs/companies']);
              },
              e => { console.log(e); }
            );
    });
  }

  cancel() {
    this.router.navigate(['/catalogs/companies']);
  }
}
