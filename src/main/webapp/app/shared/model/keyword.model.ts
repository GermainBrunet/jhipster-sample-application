export interface IKeyword {
  id?: number;
  guid?: string;
  nameId?: number;
  descriptionId?: number;
}

export class Keyword implements IKeyword {
  constructor(public id?: number, public guid?: string, public nameId?: number, public descriptionId?: number) {}
}
