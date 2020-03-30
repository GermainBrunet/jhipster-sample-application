import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILevel } from 'app/shared/model/level.model';
import { LevelService } from './level.service';
import { LevelDeleteDialogComponent } from './level-delete-dialog.component';

@Component({
  selector: 'jhi-level',
  templateUrl: './level.component.html'
})
export class LevelComponent implements OnInit, OnDestroy {
  levels?: ILevel[];
  eventSubscriber?: Subscription;

  constructor(protected levelService: LevelService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.levelService.query().subscribe((res: HttpResponse<ILevel[]>) => (this.levels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLevels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILevel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLevels(): void {
    this.eventSubscriber = this.eventManager.subscribe('levelListModification', () => this.loadAll());
  }

  delete(level: ILevel): void {
    const modalRef = this.modalService.open(LevelDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.level = level;
  }
}
