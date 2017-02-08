import { Injectable } from '@angular/core';
import { Company } from './company';

@Injectable()
export class CompanyService {
    // Mock Id Generation
    lastId: number = 0;

    // Mock placeholder for companies
    companies: Company[] = [];

    constructor(){}

    // Simulate POST /companies
    addCompany(company: Company): CompanyService {
        if (!company.id){
            company.id = ++this.lastId;
        }
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
}
