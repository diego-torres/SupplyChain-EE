export default class Country {
    id: number;
    name: string;
    countryCode: string;

    constructor (values: Object = {}){
        Object.assign(this, values);
    }
}
