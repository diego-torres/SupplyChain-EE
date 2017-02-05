import { Injectable } from '@angular/core';

import { PACKING_LISTS } from './mock-packing-lists';

@Injectable()
export class PackingListService {
    getPackingLists() {
        return Promise.resolve(PACKING_LISTS);
    }
}