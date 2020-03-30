import { ILanguageValue } from 'app/shared/model/language-value.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface ILanguageCode {
  id?: number;
  language?: Language;
  code?: string;
  languageValues?: ILanguageValue[];
}

export class LanguageCode implements ILanguageCode {
  constructor(public id?: number, public language?: Language, public code?: string, public languageValues?: ILanguageValue[]) {}
}
