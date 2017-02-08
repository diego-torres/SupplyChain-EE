import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { Login } from './login';
import { HomeComponent } from './home';
import { AuthGuard } from './guard';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'catalogs', loadChildren: 'app/catalogs/catalogs.module#CatalogsModule', canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });
