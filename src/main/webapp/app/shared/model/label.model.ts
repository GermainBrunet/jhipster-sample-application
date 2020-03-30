import { ILabelValue } from 'app/shared/model/label-value.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface ILabel {
  id?: number;
  language?: Language;
  code?: string;
  languageValues?: ILabelValue[];
  keywordId?: number;
  levelId?: number;
}

export class Label implements ILabel {
  constructor(
    public id?: number,
    public language?: Language,
    public code?: string,
    public languageValues?: ILabelValue[],
    public keywordId?: number,
    public levelId?: number
  ) {}
}
