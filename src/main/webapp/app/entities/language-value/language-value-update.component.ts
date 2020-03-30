import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILanguageValue, LanguageValue } from 'app/shared/model/language-value.model';
import { LanguageValueService } from './language-value.service';
import { ILanguageCode } from 'app/shared/model/language-code.model';
import { LanguageCodeService } from 'app/entities/language-code/language-code.service';

@Component({
  selector: 'jhi-language-value-update',
  templateUrl: './language-value-update.component.html'
})
export class LanguageValueUpdateComponent implements OnInit {
  isSaving = false;
  languagecodes: ILanguageCode[] = [];

  editForm = this.fb.group({
    id: [],
    value: [null, [Validators.required]],
    codeId: []
  });

  constructor(
    protected languageValueService: LanguageValueService,
    protected languageCodeService: LanguageCodeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ languageValue }) => {
      this.updateForm(languageValue);

      this.languageCodeService.query().subscribe((res: HttpResponse<ILanguageCode[]>) => (this.languagecodes = res.body || []));
    });
  }

  updateForm(languageValue: ILanguageValue): void {
    this.editForm.patchValue({
      id: languageValue.id,
      value: languageValue.value,
      codeId: languageValue.codeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const languageValue = this.createFromForm();
    if (languageValue.id !== undefined) {
      this.subscribeToSaveResponse(this.languageValueService.update(languageValue));
    } else {
      this.subscribeToSaveResponse(this.languageValueService.create(languageValue));
    }
  }

  private createFromForm(): ILanguageValue {
    return {
      ...new LanguageValue(),
      id: this.editForm.get(['id'])!.value,
      value: this.editForm.get(['value'])!.value,
      codeId: this.editForm.get(['codeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILanguageValue>>): void {
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

  trackById(index: number, item: ILanguageCode): any {
    return item.id;
  }
}
