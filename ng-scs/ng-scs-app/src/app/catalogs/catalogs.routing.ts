import { Routes, RouterModule } from '@angular/router';

import { Catalogs } from './catalogs.component';

import { Companies } from './companies';
import { CompanyEditorForm } from './companies/editor/editor.component';

import { Parts } from './parts';

const routes: Routes = [{
    path: '',
    component: Catalogs,
    children: [
        {path: 'companies', component: Companies},
        {path: 'companies/editor/:id', component: CompanyEditorForm},
        {path: 'parts', component: Parts},
    ]
}];

export const routing = RouterModule.forChild(routes);
