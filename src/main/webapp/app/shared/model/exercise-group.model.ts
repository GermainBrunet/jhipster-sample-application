import { IExercise } from 'app/shared/model/exercise.model';
import { IKeyword } from 'app/shared/model/keyword.model';
import { Language } from 'app/shared/model/enumerations/language.model';

export interface IExerciseGroup {
  id?: number;
  language?: Language;
  guid?: string;
  sortOrder?: number;
  name?: string;
  description?: string;
  author?: string;
  exercises?: IExercise[];
  keywords?: IKeyword[];
  levelId?: number;
}

export class ExerciseGroup implements IExerciseGroup {
  constructor(
    public id?: number,
    public language?: Language,
    public guid?: string,
    public sortOrder?: number,
    public name?: string,
    public description?: string,
    public author?: string,
    public exercises?: IExercise[],
    public keywords?: IKeyword[],
    public levelId?: number
  ) {}
}
