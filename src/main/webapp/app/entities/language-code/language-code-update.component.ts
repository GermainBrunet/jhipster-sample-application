import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILanguageCode, LanguageCode } from 'app/shared/model/language-code.model';
import { LanguageCodeService } from './language-code.service';

@Component({
  selector: 'jhi-language-code-update',
  templateUrl: './language-code-update.component.html'
})
export class LanguageCodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    language: [],
    code: [null, [Validators.required]]
  });

  constructor(protected languageCodeService: LanguageCodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ languageCode }) => {
      this.updateForm(languageCode);
    });
  }

  updateForm(languageCode: ILanguageCode): void {
    this.editForm.patchValue({
      id: languageCode.id,
      language: languageCode.language,
      code: languageCode.code
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const languageCode = this.createFromForm();
    if (languageCode.id !== undefined) {
      this.subscribeToSaveResponse(this.languageCodeService.update(languageCode));
    } else {
      this.subscribeToSaveResponse(this.languageCodeService.create(languageCode));
    }
  }

  private createFromForm(): ILanguageCode {
    return {
      ...new LanguageCode(),
      id: this.editForm.get(['id'])!.value,
      language: this.editForm.get(['language'])!.value,
      code: this.editForm.get(['code'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILanguageCode>>): void {
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
}
