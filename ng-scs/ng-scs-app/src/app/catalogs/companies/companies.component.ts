import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LocalDataSource } from 'ng2-smart-table';

import { CompanyService } from './shared/company.service';

@Component({
    selector: 'companies',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './companies.html',
    styleUrls: ['./companies.scss']
})
// TODO: Implement error from server side print out or dialog.
export class Companies implements OnInit {
    settings = {
      mode: 'external',
      noDataMessage: 'No hay datos para mostrar aquí.',
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
          roles: {
              title: 'Uso de la compañia',
              type: 'string'
          }
      }
    };

    source: LocalDataSource = new LocalDataSource();

    constructor (
      protected service: CompanyService,
      private router: Router
    ) {}

    ngOnInit(){
      this.service.getAllCompanies()
      .subscribe(
        companies => this.source.load(companies),
        e => console.log(e)
      );
    }

    onCreate(event): void {
        this.router.navigate(['/catalogs/companies/editor', 0]);
    }

    onEdit(event): void {
        let id: number = event.data.id;
        this.router.navigate(['/catalogs/companies/editor', id]);
    }

    onDelete(event): void {
      let id: number = event.data.id;
      let r: boolean = confirm('¿Desea borrar la empresa ' + event.data.name
      + '?\nConsidere que al borrar la empresa,' +
      ' toda la información relacionada con esta será eliminada del sistema,' +
      ' incluyendo listas de empaque y facturas.');
      if (r === true)
        this.service.deleteCompanyById(id)
        .subscribe(
          (data) => {},
          (err) => console.log(err),
          () => this.ngOnInit() );
    }
}
