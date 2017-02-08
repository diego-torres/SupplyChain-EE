import { Component, ViewEncapsulation } from '@angular/core';

import { CompanyService } from './shared/company.service';
import { LocalDataSource } from 'ng2-smart-table';

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
            firstName: {
                title: 'Razón Social',
                type: 'string'
            },
            lastName: {
                title: 'Ciudad',
                type: 'string'
            },
            username: {
                title: 'Estado',
                type: 'string'
            },
            email: {
                title: 'Código Postal',
                type: 'string'
            },
            age: {
                title: 'Roles de uso',
                type: 'string'
            }
        }
    };

    source: LocalDataSource = new LocalDataSource();

    constructor(protected service: CompanyService) {
        this.service.getAllCompanies().then((data) => { this.source.load(data); });
    }
}
