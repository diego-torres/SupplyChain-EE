import { State } from '../state/state';
export class Country {
    id: number;
    name: string;
    countryCode: string;
    states: State[];
}
