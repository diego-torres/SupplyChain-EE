import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators, FormArray } from '@angular/forms';

import { Part } from '../shared/part';
import { Company } from '../../company/shared/company';
import { PartService } from '../shared/part.service';
import { CompanyService } from '../../companies/shared/company.service';

import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'part-editor-form',
  templateUrl: './editor-form.html'
})
export class PartEditorForm implements OnInit, OnDestroy {
  public id: number;
  public submitted: boolean;
  public formGroupEditor: FormGroup;
  public events: any[] = [];

  private sub: any;
  constructor(
    private route: ActivatedRoute,
    private _fb: FormBuilder,
    private partService: PartService,
    private companyService: CompanyService,
    private router: Router
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    this.initPart();
    if (this.id !== 0)
      // TODO: Block with "loading"
      this.partService.getPartById(this.id)
        .subscribe(
        p => {
          console.log(p);
          this.initPart(p);
        },
        e => {
          console.log(e);
        });
  }

  initPart(partNumber?: Part) {
    if (partNumber) {
      // with values
      this.formGroupEditor = new FormGroup({
        partNumber: new FormControl(partNumber.partNumber, [
          <any>Validators.required,
          <any>Validators.minLength(5),
          <any>Validators.maxLength(150)]),
        description: new FormControl(partNumber.description),
        companyId: new FormControl(partNumber.companyId),
        weight: new FormControl(partNumber.weight),
        height: new FormControl(partNumber.height),
        width: new FormControl(partNumber.width),
        length: new FormControl(partNumber.length),
        handlingInstructions: new FormControl(partNumber.handlingInstructions),
        otherDimensions: new FormControl(partNumber.otherDimensions),
        blocked: new FormControl(partNumber.blocked)
      });
    } else
      // empty
      this.formGroupEditor = new FormGroup({
        partNumber: new FormControl('', [
          <any>Validators.required,
          <any>Validators.minLength(5),
          <any>Validators.maxLength(150)]),
        description: new FormControl(''),
        companyId: new FormControl(''),
        weight: new FormControl(''),
        height: new FormControl(''),
        width: new FormControl(''),
        length: new FormControl(''),
        handlingInstructions: new FormControl(''),
        otherDimensions: new FormControl(''),
        blocked: new FormControl(false)
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  // TODO: Implement server side validation.
  save(model: any): void {
    this.submitted = true;
    let partNumber: Part = new Part(model);
    partNumber.id = this.id;

    this.companyService.getCompanyById(partNumber.companyId)
      .subscribe((data) => {
        partNumber.company = data;
        if (partNumber.id === 0)
          this.partService.addPartNumber(partNumber)
            .subscribe(
            (postData) => this.router.navigate(['/catalogs/parts']),
            (err) => console.log(err));
        else
          this.partService.updatePartNumber(partNumber)
            .subscribe(
            (postData) => this.router.navigate(['/catalogs/parts']),
            (err) => console.log(err));
      }, (e) => console.log(e));
  }

  cancel() {
    this.router.navigate(['/catalogs/parts']);
  }
}
