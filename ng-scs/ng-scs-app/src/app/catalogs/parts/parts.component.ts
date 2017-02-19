import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LocalDataSource } from 'ng2-smart-table';

import { PartService } from './shared/part.service';

@Component({
    selector: 'parts',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './parts.html',
    styleUrls: ['./parts.scss']
})
export class PartsComponent implements OnInit {
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
        partNumber: {
            title: 'Número de parte',
            type: 'string'
        },
        description: {
            title: 'Descripción',
            type: 'string'
        },
        company_name: {
            title: 'Empresa',
            type: 'string'
        },
        blocked: {
            title: 'bloqueo',
            type: 'string'
        }
    }
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(
    private service: PartService,
    private router: Router
  ){ }

  ngOnInit() {
    this.service.getAllParts()
    .subscribe(
      p => this.source.load(p),
      e => console.log(e)
    );
  }

  onCreate(event): void {
    // TODO: Implement router to editor
    // this.router.navigate(['route/to/editor', 0]);
  }

  onEdit(event): void {
    let id: number = event.data.id;
    // TODO: Implement router to editor
    // this.router.navigate(['route/to/editor', id]);
  }

  onDelete(event): void {
    let id: number = event.data.id;
    let r: boolean = confirm('¿Desea borrar la empresa ' + event.data.name
    + '?\nConsidere que al borrar la empresa,' +
    ' toda la información relacionada con esta será eliminada del sistema,' +
    ' incluyendo listas de empaque y facturas.');
    // TODO: Implement delete service operation.
    /*if (r === true)
      this.service.deleteCompanyById(id)
      .subscribe(
        (data) => {},
        (err) => console.log(err),
        () => this.ngOnInit() );*/
  }
}
