import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILabelValue, LabelValue } from 'app/shared/model/label-value.model';
import { LabelValueService } from './label-value.service';
import { ILabel } from 'app/shared/model/label.model';
import { LabelService } from 'app/entities/label/label.service';

@Component({
  selector: 'jhi-label-value-update',
  templateUrl: './label-value-update.component.html'
})
export class LabelValueUpdateComponent implements OnInit {
  isSaving = false;
  labels: ILabel[] = [];

  editForm = this.fb.group({
    id: [],
    value: [null, [Validators.required]],
    codeId: []
  });

  constructor(
    protected labelValueService: LabelValueService,
    protected labelService: LabelService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ labelValue }) => {
      this.updateForm(labelValue);

      this.labelService.query().subscribe((res: HttpResponse<ILabel[]>) => (this.labels = res.body || []));
    });
  }

  updateForm(labelValue: ILabelValue): void {
    this.editForm.patchValue({
      id: labelValue.id,
      value: labelValue.value,
      codeId: labelValue.codeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const labelValue = this.createFromForm();
    if (labelValue.id !== undefined) {
      this.subscribeToSaveResponse(this.labelValueService.update(labelValue));
    } else {
      this.subscribeToSaveResponse(this.labelValueService.create(labelValue));
    }
  }

  private createFromForm(): ILabelValue {
    return {
      ...new LabelValue(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      codeId: this.editForm.get(['codeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILabelValue>>): void {
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
