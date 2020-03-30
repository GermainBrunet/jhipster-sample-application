import { IExerciseGroup } from 'app/shared/model/exercise-group.model';

export interface ILevel {
  id?: number;
  guid?: string;
  nameId?: number;
  descriptionId?: number;
  exerciseGroups?: IExerciseGroup[];
}

export class Level implements ILevel {
  constructor(
    public id?: number,
    public guid?: string,
    public nameId?: number,
    public descriptionId?: number,
    public exerciseGroups?: IExerciseGroup[]
  ) {}
}
