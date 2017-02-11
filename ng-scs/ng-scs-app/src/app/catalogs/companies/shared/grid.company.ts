export class GridCompany {
    id: number;
    companyName: string;
    companyRoles: string;
    companyTaxId: string;
    companyCountry: string;
    companyAddressCity: string;
    companyAddressState: string;

    constructor (values: Object = {}){
        Object.assign(this, values);
    }
}
