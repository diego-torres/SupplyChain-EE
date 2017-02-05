import { Component } from '@angular/core';

import { PackingListService } from './packing-list/shared/packing-list.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ PackingListService ]
})
export class AppComponent {
  title = 'app works!';
}
