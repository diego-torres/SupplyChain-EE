import Country from '../country/country';

export default class Address {
    id: number;
    streetAddress: string;
    additionalAddressInfo: string;
    city: string;
    addressState: string;
    zip: string;
    country: Country;
    landLine: string;
    contactName: string;
    addressType: string;

    constructor (values: Object = {}){
        Object.assign(this, values);
    }
}
