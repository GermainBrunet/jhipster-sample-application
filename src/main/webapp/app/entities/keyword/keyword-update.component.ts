import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IKeyword, Keyword } from 'app/shared/model/keyword.model';
import { KeywordService } from './keyword.service';
import { ILabel } from 'app/shared/model/label.model';
import { LabelService } from 'app/entities/label/label.service';

@Component({
  selector: 'jhi-keyword-update',
  templateUrl: './keyword-update.component.html'
})
export class KeywordUpdateComponent implements OnInit {
  isSaving = false;
  labels: ILabel[] = [];

  editForm = this.fb.group({
    id: [],
    guid: [null, [Validators.required]],
    labelId: []
  });

  constructor(
    protected keywordService: KeywordService,
    protected labelService: LabelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ keyword }) => {
      this.updateForm(keyword);

      this.labelService
        .query({ filter: 'keyword-is-null' })
        .pipe(
          map((res: HttpResponse<ILabel[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILabel[]) => {
          if (!keyword.labelId) {
            this.labels = resBody;
          } else {
            this.labelService
              .find(keyword.labelId)
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

  updateForm(keyword: IKeyword): void {
    this.editForm.patchValue({
      id: keyword.id,
      guid: keyword.guid,
      labelId: keyword.labelId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const keyword = this.createFromForm();
    if (keyword.id !== undefined) {
      this.subscribeToSaveResponse(this.keywordService.update(keyword));
    } else {
      this.subscribeToSaveResponse(this.keywordService.create(keyword));
    }
  }

  private createFromForm(): IKeyword {
    return {
      ...new Keyword(),
      id: this.editForm.get(['id'])!.value,
      guid: this.editForm.get(['guid'])!.value,
      labelId: this.editForm.get(['labelId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKeyword>>): void {
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
