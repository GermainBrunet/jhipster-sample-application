import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILabelValue } from 'app/shared/model/label-value.model';
import { LabelValueService } from './label-value.service';

@Component({
  templateUrl: './label-value-delete-dialog.component.html'
})
export class LabelValueDeleteDialogComponent {
  labelValue?: ILabelValue;

  constructor(
    protected labelValueService: LabelValueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.labelValueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('labelValueListModification');
      this.activeModal.close();
    });
  }
}
