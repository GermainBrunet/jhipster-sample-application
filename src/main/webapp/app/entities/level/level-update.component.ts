import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ILevel, Level } from 'app/shared/model/level.model';
import { LevelService } from './level.service';
import { ILabel } from 'app/shared/model/label.model';
import { LabelService } from 'app/entities/label/label.service';

@Component({
  selector: 'jhi-level-update',
  templateUrl: './level-update.component.html'
})
export class LevelUpdateComponent implements OnInit {
  isSaving = false;
  labels: ILabel[] = [];

  editForm = this.fb.group({
    id: [],
    guid: [null, [Validators.required]],
    labelId: []
  });

  constructor(
    protected levelService: LevelService,
    protected labelService: LabelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ level }) => {
      this.updateForm(level);

      this.labelService
        .query({ filter: 'level-is-null' })
        .pipe(
          map((res: HttpResponse<ILabel[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILabel[]) => {
          if (!level.labelId) {
            this.labels = resBody;
          } else {
            this.labelService
              .find(level.labelId)
              .pipe(
                map((subRes: HttpResponse<ILabel>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILabel[]) => (this.labels = concatRes));
          }
        });
    });
  }

  updateForm(level: ILevel): void {
    this.editForm.patchValue({
      id: level.id,
      guid: level.guid,
      labelId: level.labelId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const level = this.createFromForm();
    if (level.id !== undefined) {
      this.subscribeToSaveResponse(this.levelService.update(level));
    } else {
      this.subscribeToSaveResponse(this.levelService.create(level));
    }
  }

  private createFromForm(): ILevel {
    return {
      ...new Level(),
      id: this.editForm.get(['id'])!.value,
      guid: this.editForm.get(['guid'])!.value,
      labelId: this.editForm.get(['labelId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevel>>): void {
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

  trackById(index: number, item: ILabel): any {
    return item.id;
  }
}
