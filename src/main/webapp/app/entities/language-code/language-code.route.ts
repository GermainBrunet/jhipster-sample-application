import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILanguageCode, LanguageCode } from 'app/shared/model/language-code.model';
import { LanguageCodeService } from './language-code.service';
import { LanguageCodeComponent } from './language-code.component';
import { LanguageCodeDetailComponent } from './language-code-detail.component';
import { LanguageCodeUpdateComponent } from './language-code-update.component';

@Injectable({ providedIn: 'root' })
export class LanguageCodeResolve implements Resolve<ILanguageCode> {
  constructor(private service: LanguageCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILanguageCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((languageCode: HttpResponse<LanguageCode>) => {
          if (languageCode.body) {
            return of(languageCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LanguageCode());
  }
}

export const languageCodeRoute: Routes = [
  {
    path: '',
    component: LanguageCodeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterSampleApplicationApp.languageCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LanguageCodeDetailComponent,
    resolve: {
      languageCode: LanguageCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LanguageCodeUpdateComponent,
    resolve: {
      languageCode: LanguageCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LanguageCodeUpdateComponent,
    resolve: {
      languageCode: LanguageCodeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.languageCode.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
