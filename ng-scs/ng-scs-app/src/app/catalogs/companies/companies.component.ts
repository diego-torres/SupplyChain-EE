import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';

import { LocalDataSource } from 'ng2-smart-table';

import { CompanyService } from './shared/company.service';

@Component({
    selector: 'companies',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './companies.html',
    styleUrls: ['./companies.scss']
})
export class Companies {
    query: string = '';

    settings = {
        mode: 'external',
        noDataMessage: 'No hay datos para mostrar aquí, o se ha perdido la conexión con la base de datos.',
        add: {
            addButtonContent: '<i class="ion-ios-plus-outline"></i>',
            createButtonContent: '<i class="ion-checkmark"></i>',
            cancelButtonContent: '<i class="ion-close"></i>',
        },
        edit: {
            editButtonContent: '<i class="ion-edit"></i>',
            saveButtonContent: '<i class="ion-checkmark"></i>',
            cancelButtonContent: '<i class="ion-close"></i>',
        },
        delete: {
            deleteButtonContent: '<i class="ion-trash-a"></i>',
            confirmDelete: true
        },
        columns: {
            id: {
                title: 'ID',
                type: 'number'
            },
            name: {
                title: 'Razón Social',
                type: 'string'
            },
            taxId: {
                title: 'RFC',
                type: 'string'
            },
            email: {
                title: 'email',
                type: 'string'
            },
            roles_ES: {
                title: 'Uso de la compañia',
                type: 'string'
            }
        }
    };

    source: LocalDataSource = new LocalDataSource();

    constructor(protected service: CompanyService, private router: Router) {
        this.service.getAllCompanies().then((data) => { this.source.load(data); });
    }

    onCreate(event): void {
        this.router.navigate(['/catalogs/companies/editor', 0]);
    }

    onEdit(event): void{
        let id: number = event.data.id;
        this.router.navigate(['/catalogs/companies/editor', id]);
    }
}
