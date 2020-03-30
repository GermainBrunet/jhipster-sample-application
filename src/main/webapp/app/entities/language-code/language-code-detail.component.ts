import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILanguageCode } from 'app/shared/model/language-code.model';

@Component({
  selector: 'jhi-language-code-detail',
  templateUrl: './language-code-detail.component.html'
})
export class LanguageCodeDetailComponent implements OnInit {
  languageCode: ILanguageCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ languageCode }) => (this.languageCode = languageCode));
  }

  previousState(): void {
    window.history.back();
  }
}
