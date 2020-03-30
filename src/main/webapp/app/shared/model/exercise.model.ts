export interface IExercise {
  id?: number;
  guid?: string;
  sortOrder?: number;
  initialWord?: string;
  targetWord?: string;
  readInstructions?: string;
  writtenInstructions?: string;
  exerciseGroupId?: number;
}

export class Exercise implements IExercise {
  constructor(
    public id?: number,
    public guid?: string,
    public sortOrder?: number,
    public initialWord?: string,
    public targetWord?: string,
    public readInstructions?: string,
    public writtenInstructions?: string,
    public exerciseGroupId?: number
  ) {}
}
