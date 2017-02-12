import { State } from '../state/state';

export class Address {
    id: number;
    streetAddress: string;
    additionalAddressInfo: string;
    city: string;
    geoState: State;
    zip: string;
    landLine: string;
    contactName: string;
    addressType: string;
    stateId: number;
    countryId: number;
}
