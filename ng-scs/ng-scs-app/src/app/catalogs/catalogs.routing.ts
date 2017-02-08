import { Routes, RouterModule } from '@angular/router';

import { Catalogs } from './catalogs.component';
import { Companies } from './companies';
import { Parts } from './parts';

const routes: Routes = [{
    path: '',
    component: Catalogs,
    children: [
        {path: 'companies', component: Companies},
        {path: 'parts', component: Parts},
    ]
}];

export const routing = RouterModule.forChild(routes);
