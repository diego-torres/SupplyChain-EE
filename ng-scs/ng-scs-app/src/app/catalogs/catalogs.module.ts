import { NgModule }      from '@angular/core';
import { CommonModule }  from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgaModule } from '../theme/nga.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { routing }       from './catalogs.routing';

import { RatingModule } from 'ng2-bootstrap';
import { Catalogs } from './catalogs.component';
import { Companies } from './companies';
import { CompanyEditorForm } from './companies/editor/editor.component';
import { PartsComponent } from './parts';
import { PartEditorForm } from './parts/editor/editor.component';
import { AddressComponent } from '../geo/address/address.component';

import { CompanyService } from './companies/shared/company.service';
import { CountryService } from '../geo/country/country.service';
import { StateService } from '../geo/state/state.service';
import { PartService } from './parts/shared/part.service';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgaModule,
    RatingModule.forRoot(),
    routing,
    Ng2SmartTableModule
  ],
  declarations: [
    Catalogs,
    Companies,
    CompanyEditorForm,
    AddressComponent,
    PartsComponent,
    PartEditorForm
  ],
  providers: [
    CompanyService,
    CountryService,
    StateService,
    PartService
  ]
})
export class CatalogsModule {
}
