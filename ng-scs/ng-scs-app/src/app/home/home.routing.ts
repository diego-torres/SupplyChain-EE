import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { ModuleWithProviders } from '@angular/core';

import { Login } from '../login';
import { AuthGuard } from '../guard';

export const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent,
        children: [
            { path: '', redirectTo: 'dashboard', pathMatch: 'full', canActivate: [AuthGuard] },
            { path: 'dashboard',
            loadChildren: 'app/dashboard/dashboard.module#DashboardModule', canActivate: [AuthGuard] }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
