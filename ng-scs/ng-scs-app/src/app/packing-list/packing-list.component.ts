import { Component, OnInit } from '@angular/core';

import { PackingList } from './shared/packing-list.model';
import { PackingListService } from './shared/packing-list.service';

@Component({
  selector: 'packing-list',
  templateUrl: './packing-list.component.html',
  styleUrls: ['./packing-list.component.css']
})

export class PackingListComponent implements OnInit {
    packingLists: PackingList[] = [];

    constructor(private packingListService: PackingListService){}
    ngOnInit(){
        this.packingListService.getPackingLists()
        .then(packingLists => this.packingLists = packingLists );
    }
}