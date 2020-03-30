import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IExercise, Exercise } from 'app/shared/model/exercise.model';
import { ExerciseService } from './exercise.service';
import { IExerciseGroup } from 'app/shared/model/exercise-group.model';
import { ExerciseGroupService } from 'app/entities/exercise-group/exercise-group.service';

@Component({
  selector: 'jhi-exercise-update',
  templateUrl: './exercise-update.component.html'
})
export class ExerciseUpdateComponent implements OnInit {
  isSaving = false;
  exercisegroups: IExerciseGroup[] = [];

  editForm = this.fb.group({
    id: [],
    guid: [null, [Validators.required]],
    sortOrder: [null, [Validators.required]],
    initialWord: [null, [Validators.required]],
    targetWord: [null, [Validators.required]],
    readInstructions: [null, [Validators.required]],
    writtenInstructions: [null, [Validators.required]],
    exerciseGroupId: []
  });

  constructor(
    protected exerciseService: ExerciseService,
    protected exerciseGroupService: ExerciseGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ exercise }) => {
      this.updateForm(exercise);

      this.exerciseGroupService.query().subscribe((res: HttpResponse<IExerciseGroup[]>) => (this.exercisegroups = res.body || []));
    });
  }

  updateForm(exercise: IExercise): void {
    this.editForm.patchValue({
      id: exercise.id,
      guid: exercise.guid,
      sortOrder: exercise.sortOrder,
      initialWord: exercise.initialWord,
      targetWord: exercise.targetWord,
      readInstructions: exercise.readInstructions,
      writtenInstructions: exercise.writtenInstructions,
      exerciseGroupId: exercise.exerciseGroupId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const exercise = this.createFromForm();
    if (exercise.id !== undefined) {
      this.subscribeToSaveResponse(this.exerciseService.update(exercise));
    } else {
      this.subscribeToSaveResponse(this.exerciseService.create(exercise));
    }
  }

  private createFromForm(): IExercise {
    return {
      ...new Exercise(),
      id: this.editForm.get(['id'])!.value,
      guid: this.editForm.get(['guid'])!.value,
      sortOrder: this.editForm.get(['sortOrder'])!.value,
      initialWord: this.editForm.get(['initialWord'])!.value,
      targetWord: this.editForm.get(['targetWord'])!.value,
      readInstructions: this.editForm.get(['readInstructions'])!.value,
      writtenInstructions: this.editForm.get(['writtenInstructions'])!.value,
      exerciseGroupId: this.editForm.get(['exerciseGroupId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExercise>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IExerciseGroup): any {
    return item.id;
  }
}
