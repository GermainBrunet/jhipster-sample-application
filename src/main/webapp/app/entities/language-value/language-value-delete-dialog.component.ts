import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILanguageValue } from 'app/shared/model/language-value.model';
import { LanguageValueService } from './language-value.service';

@Component({
  templateUrl: './language-value-delete-dialog.component.html'
})
export class LanguageValueDeleteDialogComponent {
  languageValue?: ILanguageValue;

  constructor(
    protected languageValueService: LanguageValueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.languageValueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('languageValueListModification');
      this.activeModal.close();
    });
  }
}
