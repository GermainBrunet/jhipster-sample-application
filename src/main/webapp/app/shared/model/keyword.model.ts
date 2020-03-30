import { IExerciseGroup } from 'app/shared/model/exercise-group.model';

export interface IKeyword {
  id?: number;
  guid?: string;
  labelId?: number;
  exerciseGroups?: IExerciseGroup[];
}

export class Keyword implements IKeyword {
  constructor(public id?: number, public guid?: string, public labelId?: number, public exerciseGroups?: IExerciseGroup[]) {}
}
