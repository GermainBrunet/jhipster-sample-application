export interface ILabelValue {
  id?: number;
  value?: string;
  codeId?: number;
}

export class LabelValue implements ILabelValue {
  constructor(public id?: number, public value?: string, public codeId?: number) {}
}
