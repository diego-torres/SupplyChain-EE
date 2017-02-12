import { Injectable } from '@angular/core';
import { Country } from './country';

@Injectable()
export class CountryService {

    // Mock placeholder for companies
    countries: Country[] = [
        {id: 1, name: 'Mexico', countryCode: 'MEX'},
        {id: 2, name: 'United States', countryCode: 'USA'}];

    constructor(){}

    // Simulate GET /countries
    getAllCountries(): Promise<Country[]> {
        return new Promise((resolve, reject) => {
            resolve(this.countries);
        });
    }

    // Simulate GET /countries/:id
    getCountryById(id: number): Country {
        return this.countries.filter(country => country.id === id).pop();
    }
}
