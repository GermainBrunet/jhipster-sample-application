import { IExerciseGroup } from 'app/shared/model/exercise-group.model';

export interface ILevel {
  id?: number;
  guid?: string;
  labelId?: number;
  exerciseGroups?: IExerciseGroup[];
}

export class Level implements ILevel {
  constructor(public id?: number, public guid?: string, public labelId?: number, public exerciseGroups?: IExerciseGroup[]) {}
}
