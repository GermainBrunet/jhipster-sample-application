import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILanguageCode } from 'app/shared/model/language-code.model';
import { LanguageCodeService } from './language-code.service';

@Component({
  templateUrl: './language-code-delete-dialog.component.html'
})
export class LanguageCodeDeleteDialogComponent {
  languageCode?: ILanguageCode;

  constructor(
    protected languageCodeService: LanguageCodeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.languageCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('languageCodeListModification');
      this.activeModal.close();
    });
  }
}
