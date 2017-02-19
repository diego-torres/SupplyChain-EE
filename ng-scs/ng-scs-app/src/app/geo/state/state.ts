import { Country } from '../country/country';
export class State {
    id: number;
    name: string;
    conventionalAbreviation: string;
    country?: Country;
    constructor (values: Object = {}) {
        console.log('State constructor value assignment from', values);
        Object.assign(this, values);
    }
}
