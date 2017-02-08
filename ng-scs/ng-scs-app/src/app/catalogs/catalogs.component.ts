import { Component } from '@angular/core';
import { Routes } from '@angular/router';

import { BaMenuService } from '../theme';
import { MENU } from '../app.menu';

@Component ({
    templateUrl: './catalogs.html'
})

export class Catalogs {
    constructor( private _menuService: BaMenuService ) {}

    ngOnInit() {
        this._menuService.updateMenuByRoutes(<Routes>MENU);
    }
}
