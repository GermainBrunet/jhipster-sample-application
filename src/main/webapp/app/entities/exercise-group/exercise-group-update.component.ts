import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IExerciseGroup, ExerciseGroup } from 'app/shared/model/exercise-group.model';
import { ExerciseGroupService } from './exercise-group.service';
import { IKeyword } from 'app/shared/model/keyword.model';
import { KeywordService } from 'app/entities/keyword/keyword.service';
import { ILevel } from 'app/shared/model/level.model';
import { LevelService } from 'app/entities/level/level.service';

type SelectableEntity = IKeyword | ILevel;

@Component({
  selector: 'jhi-exercise-group-update',
  templateUrl: './exercise-group-update.component.html'
})
export class ExerciseGroupUpdateComponent implements OnInit {
  isSaving = false;
  keywords: IKeyword[] = [];
  levels: ILevel[] = [];

  editForm = this.fb.group({
    id: [],
    language: [],
    guid: [null, [Validators.required]],
    sortOrder: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [],
    author: [],
    keywords: [],
    levelId: []
  });

  constructor(
    protected exerciseGroupService: ExerciseGroupService,
    protected keywordService: KeywordService,
    protected levelService: LevelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ exerciseGroup }) => {
      this.updateForm(exerciseGroup);

      this.keywordService.query().subscribe((res: HttpResponse<IKeyword[]>) => (this.keywords = res.body || []));

      this.levelService.query().subscribe((res: HttpResponse<ILevel[]>) => (this.levels = res.body || []));
    });
  }

  updateForm(exerciseGroup: IExerciseGroup): void {
    this.editForm.patchValue({
      id: exerciseGroup.id,
      language: exerciseGroup.language,
      guid: exerciseGroup.guid,
      sortOrder: exerciseGroup.sortOrder,
      name: exerciseGroup.name,
      description: exerciseGroup.description,
      author: exerciseGroup.author,
      keywords: exerciseGroup.keywords,
      levelId: exerciseGroup.levelId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const exerciseGroup = this.createFromForm();
    if (exerciseGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.exerciseGroupService.update(exerciseGroup));
    } else {
      this.subscribeToSaveResponse(this.exerciseGroupService.create(exerciseGroup));
    }
  }

  private createFromForm(): IExerciseGroup {
    return {
      ...new ExerciseGroup(),
      id: this.editForm.get(['id'])!.value,
      language: this.editForm.get(['language'])!.value,
      guid: this.editForm.get(['guid'])!.value,
      sortOrder: this.editForm.get(['sortOrder'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      author: this.editForm.get(['author'])!.value,
      keywords: this.editForm.get(['keywords'])!.value,
      levelId: this.editForm.get(['levelId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExerciseGroup>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IKeyword[], option: IKeyword): IKeyword {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
