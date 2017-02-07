import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { routing } from './home.routing';
import { NgaModule } from '../theme/nga.module';

import { HomeComponent } from './home.component';

@NgModule({
    imports: [
        CommonModule,
        NgaModule,
        routing
    ],
    declarations: [
        HomeComponent
    ]
})
export class HomeModule{}
