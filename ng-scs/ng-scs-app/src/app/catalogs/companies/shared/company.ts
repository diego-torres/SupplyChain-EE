import Address from '../../../geo/address/address';

export interface Company {
    id: number;
    name: string;
    taxId: string;
    email: string;
    companyRole: number;
    roles?: string[];
    addresses?: Address[];
}
