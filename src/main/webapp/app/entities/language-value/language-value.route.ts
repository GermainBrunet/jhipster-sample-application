import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILanguageValue, LanguageValue } from 'app/shared/model/language-value.model';
import { LanguageValueService } from './language-value.service';
import { LanguageValueComponent } from './language-value.component';
import { LanguageValueDetailComponent } from './language-value-detail.component';
import { LanguageValueUpdateComponent } from './language-value-update.component';

@Injectable({ providedIn: 'root' })
export class LanguageValueResolve implements Resolve<ILanguageValue> {
  constructor(private service: LanguageValueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILanguageValue> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((languageValue: HttpResponse<LanguageValue>) => {
          if (languageValue.body) {
            return of(languageValue.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LanguageValue());
  }
}

export const languageValueRoute: Routes = [
  {
    path: '',
    component: LanguageValueComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterSampleApplicationApp.languageValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageValueDetailComponent,
    resolve: {
      languageValue: LanguageValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageValueUpdateComponent,
    resolve: {
      languageValue: LanguageValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageValueUpdateComponent,
    resolve: {
      languageValue: LanguageValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
