import { ILanguageValue } from 'app/shared/model/language-value.model';

export interface ILanguageCode {
  id?: number;
  code?: string;
  languageValues?: ILanguageValue[];
}

export class LanguageCode implements ILanguageCode {
  constructor(public id?: number, public code?: string, public languageValues?: ILanguageValue[]) {}
}
