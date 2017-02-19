import { Company } from '../../companies/shared/company';

export class Part {
  public id: number = 0;
  public partNumber: string;
  public description: string;
  public company: Company;
  public weight: number;
  public height: number;
  public width: number;
  public length: number;
  public blocked: boolean;
  public handlingInstructions: string;
  public otherDimensions: string;

  constructor (values: Object = {}) {
    Object.assign(this, values);
  }
}
