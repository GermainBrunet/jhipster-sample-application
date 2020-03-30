import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILabelValue, LabelValue } from 'app/shared/model/label-value.model';
import { LabelValueService } from './label-value.service';
import { LabelValueComponent } from './label-value.component';
import { LabelValueDetailComponent } from './label-value-detail.component';
import { LabelValueUpdateComponent } from './label-value-update.component';

@Injectable({ providedIn: 'root' })
export class LabelValueResolve implements Resolve<ILabelValue> {
  constructor(private service: LabelValueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILabelValue> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((labelValue: HttpResponse<LabelValue>) => {
          if (labelValue.body) {
            return of(labelValue.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LabelValue());
  }
}

export const labelValueRoute: Routes = [
  {
    path: '',
    component: LabelValueComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterSampleApplicationApp.labelValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LabelValueDetailComponent,
    resolve: {
      labelValue: LabelValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.labelValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LabelValueUpdateComponent,
    resolve: {
      labelValue: LabelValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.labelValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LabelValueUpdateComponent,
    resolve: {
      labelValue: LabelValueResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.labelValue.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
