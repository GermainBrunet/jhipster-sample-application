import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILanguageValue } from 'app/shared/model/language-value.model';

@Component({
  selector: 'jhi-language-value-detail',
  templateUrl: './language-value-detail.component.html'
})
export class LanguageValueDetailComponent implements OnInit {
  languageValue: ILanguageValue | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ languageValue }) => (this.languageValue = languageValue));
  }

  previousState(): void {
    window.history.back();
  }
}
