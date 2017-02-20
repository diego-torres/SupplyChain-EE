import { Routes, RouterModule } from '@angular/router';

import { Catalogs } from './catalogs.component';

import { Companies } from './companies';
import { CompanyEditorForm } from './companies/editor/editor.component';

import { PartsComponent } from './parts';
import { PartEditorForm } from './parts/editor/editor.component';

const routes: Routes = [{
    path: '',
    component: Catalogs,
    children: [
        {path: 'companies', component: Companies},
        {path: 'companies/editor/:id', component: CompanyEditorForm},
        {path: 'parts', component: PartsComponent},
        {path: 'parts/editor/:id', component: PartEditorForm},
    ]
}];

export const routing = RouterModule.forChild(routes);
