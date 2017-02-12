import { Address }  from '../../../geo/address/address';

export class Company {
    public id: number = 0;
    public name: string;
    public taxId: string;
    public email: string;
    public rolesArray: string[] = [];
    public addresses?: Address[];

    constructor (values: Object = {}) {
        console.log('Company constructor value assignment from', values);
        Object.assign(this, values);
    }

    public get buyer(): boolean {
        return this.rolesArray.indexOf('buyer') !== -1;
    }

    public set buyer(value: boolean) {
        if (value && !this.buyer)
            this.rolesArray.push('buyer');
        else if (this.buyer)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'buyer');
    }

    public get seller(): boolean {
        return this.rolesArray.indexOf('seller') !== -1;
    }

    public set seller(value: boolean) {
        if (value && !this.seller)
            this.rolesArray.push('seller');
        else if (this.seller)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'seller');
    }

    public get sender(): boolean {
        return this.rolesArray.indexOf('sender') !== -1;
    }

    public set sender(value: boolean) {
        if (value && !this.sender)
            this.rolesArray.push('sender');
        else if (this.sender)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'sender');
    }

    public get receiver(): boolean {
        return this.rolesArray.indexOf('receiver') !== -1;
    }

    public set receiver(value: boolean) {
        if (value && !this.receiver)
            this.rolesArray.push('receiver');
        else if (this.receiver)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'receiver');
    }

    public get freighter(): boolean {
       return this.rolesArray.indexOf('freighter') !== -1;
    }

    public set freighter(value: boolean) {
        if (value && !this.freighter)
            this.rolesArray.push('freighter');
        else if (this.freighter)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'freighter');
    }

    public get trader(): boolean {
        return this.rolesArray.indexOf('trader') !== -1;
    }

    public set trader(value: boolean) {
        if (value && !this.trader)
            this.rolesArray.push('trader');
        else if (this.trader)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'trader');
    }

    public get billable(): boolean {
        return this.rolesArray.indexOf('billable') !== -1;
    }

    public set billable(value: boolean) {
        if (value && !this.billable)
            this.rolesArray.push('billable');
        else if (this.billable)
            this.rolesArray = this.rolesArray.filter(rol => rol !== 'billable');
    }

    public get roles_ES(): string {
        let roles: string = '';
        if (this.buyer)
            roles = 'comprador, ';
        if (this.seller)
            roles = roles + 'vendedor, ';
        if (this.sender)
            roles = roles + 'remitente, ';
        if (this.receiver)
            roles = roles + 'destinatario, ';
        if (this.freighter)
            roles = roles + 'transportista, ';
        if (this.trader)
            roles = roles + 'agente aduanal, ';
        if (this.billable)
            roles = roles + 'facturable, ';

        // remove last comma
        if (roles.length > 2)
            roles = roles.substring(0, roles.length - 2);

        return roles;
    }

    public get roles(): string{
        return this.rolesArray.join(', ');
    }
}
