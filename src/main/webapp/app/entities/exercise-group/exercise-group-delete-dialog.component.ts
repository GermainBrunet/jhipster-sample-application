import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExerciseGroup } from 'app/shared/model/exercise-group.model';
import { ExerciseGroupService } from './exercise-group.service';

@Component({
  templateUrl: './exercise-group-delete-dialog.component.html'
})
export class ExerciseGroupDeleteDialogComponent {
  exerciseGroup?: IExerciseGroup;

  constructor(
    protected exerciseGroupService: ExerciseGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.exerciseGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('exerciseGroupListModification');
      this.activeModal.close();
    });
  }
}
