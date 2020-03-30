import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILanguageValue } from 'app/shared/model/language-value.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LanguageValueService } from './language-value.service';
import { LanguageValueDeleteDialogComponent } from './language-value-delete-dialog.component';

@Component({
  selector: 'jhi-language-value',
  templateUrl: './language-value.component.html'
})
export class LanguageValueComponent implements OnInit, OnDestroy {
  languageValues?: ILanguageValue[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected languageValueService: LanguageValueService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.languageValueService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ILanguageValue[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInLanguageValues();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILanguageValue): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLanguageValues(): void {
    this.eventSubscriber = this.eventManager.subscribe('languageValueListModification', () => this.loadPage());
  }

  delete(languageValue: ILanguageValue): void {
    const modalRef = this.modalService.open(LanguageValueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.languageValue = languageValue;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ILanguageValue[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/language-value'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.languageValues = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
