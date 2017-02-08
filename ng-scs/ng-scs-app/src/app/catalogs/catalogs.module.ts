import { NgModule }      from '@angular/core';
import { CommonModule }  from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgaModule } from '../theme/nga.module';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { routing }       from './catalogs.routing';

import { RatingModule } from 'ng2-bootstrap';
import { Catalogs } from './catalogs.component';
import { Companies } from './companies';
import { Parts } from './parts';

import { CompanyService } from './companies/shared/company.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgaModule,
    RatingModule.forRoot(),
    routing,
    Ng2SmartTableModule
  ],
  declarations: [
    Catalogs,
    Companies,
    Parts
  ],
  providers: [
    CompanyService
  ]
})
export class CatalogsModule {
}
