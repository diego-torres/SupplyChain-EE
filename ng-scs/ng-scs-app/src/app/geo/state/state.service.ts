import { Injectable } from '@angular/core';
import { State } from './state';

@Injectable()
export class StateService {

    // Mock placeholder for companies
    states: State[] = [
        {
            id: 1,
            name: 'Texas',
            conventionalAbreviation: 'TX'
        }, {
            id: 2,
            name: 'New Mexico',
            conventionalAbreviation: 'NM'
        }];

    constructor(){}

    // Simulate GET /states
    getStatesByCountryId(countryId: number): Promise<State[]> {
        return new Promise((resolve, reject) => {
            resolve(this.states);
        });
    }

    // Simulate GET /countries/:id
    getStateById(id: number): State {
        return this.states[1];
    }
}
