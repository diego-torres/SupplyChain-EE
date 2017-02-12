import { Injectable } from '@angular/core';
import { Company } from './company';

import { Address } from '../../../geo/address/address';
import { State } from '../../../geo/state/state';
import { StateService } from '../../../geo/state/state.service';

@Injectable()
export class CompanyService {
    // Mock Id Generation
    lastId: number = 0;

    // Mock placeholder for companies
    companies: Company[] = [];

    constructor(private stateService: StateService){}

    // Simulate POST /companies
    addCompany(company: Company): CompanyService {
        if (!company.id){
            company.id = ++this.lastId;
        }

        // fix addresses
        company.addresses.forEach(address => { this.fixAddress(address); });

        console.log('REST send (CREATE - POST): ', company);
        this.companies.push(company);
        return this;
    }

    // Simulate DELETE /companies/:id
    deleteCompanyById(id: number): CompanyService {
        this.companies = this.companies.filter(company => company.id !== id);
        return this;
    }

    // Simulate PUT /companies/:id
    updateCompanyById(id: number, values: Object = {}): Company {
        let company = this.getCompanyById(id);
        if (!company){
            return null;
        }
        Object.assign(company, values);

        // calculate geoState
        // fix addresses
        company.addresses.forEach(address => { this.fixAddress(address); });

        console.log('REST send (UPDATE - PUT): ', company);
        return company;
    }

    // Simulate GET /companies
    getAllCompanies(): Promise<Company[]> {
        return new Promise((resolve, reject) => {
            resolve(this.companies);
        });
    }

    // Simulate GET /companies/:id
    getCompanyById(id: number): Company {
        return this.companies.filter(company => company.id === id).pop();
    }

    private fixAddress(address: Address) {
        if (address.stateId && address.stateId > 0) {
            let geoState = this.stateService.getStateById(address.stateId);
            address.geoState = geoState;
        }
    }
}
