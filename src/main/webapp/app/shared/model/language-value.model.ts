export interface ILanguageValue {
  id?: number;
  value?: string;
  codeId?: number;
}

export class LanguageValue implements ILanguageValue {
  constructor(public id?: number, public value?: string, public codeId?: number) {}
}
