import { IKeyword } from 'app/shared/model/keyword.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface IExerciseGroup {
  id?: number;
  language?: Language;
  guid?: string;
  sortOrder?: number;
  author?: string;
  nameId?: number;
  descriptionId?: number;
  keywords?: IKeyword[];
  levelId?: number;
}

export class ExerciseGroup implements IExerciseGroup {
  constructor(
    public id?: number,
    public language?: Language,
    public guid?: string,
    public sortOrder?: number,
    public author?: string,
    public nameId?: number,
    public descriptionId?: number,
    public keywords?: IKeyword[],
    public levelId?: number
  ) {}
}
