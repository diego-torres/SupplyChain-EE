import Address from '../../../geo/address/address';

export class Company {
    id: number;
    name: string;
    roles: string[];
    addresses: Address[];
    constructor (values: Object = {}){
        Object.assign(this, values);
    }
}
