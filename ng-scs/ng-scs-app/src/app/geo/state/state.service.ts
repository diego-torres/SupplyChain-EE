import { Injectable } from '@angular/core';
import { State } from './state';

@Injectable()
export class StateService {

    // Mock placeholder for companies
    states: State[] = [
        {
            id: 1,
            name: 'Texas',
            conventionalAbreviation: 'TX',
            country: {id: 2, name: 'United States', countryCode: 'USA'}
        }, {
            id: 2,
            name: 'New Mexico',
            conventionalAbreviation: 'NM',
            country: {id: 2, name: 'United States', countryCode: 'USA'}
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
        return this.states.filter(state => state.id === id).pop();
    }
}
