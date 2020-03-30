import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IExerciseGroup, ExerciseGroup } from 'app/shared/model/exercise-group.model';
import { ExerciseGroupService } from './exercise-group.service';
import { ExerciseGroupComponent } from './exercise-group.component';
import { ExerciseGroupDetailComponent } from './exercise-group-detail.component';
import { ExerciseGroupUpdateComponent } from './exercise-group-update.component';

@Injectable({ providedIn: 'root' })
export class ExerciseGroupResolve implements Resolve<IExerciseGroup> {
  constructor(private service: ExerciseGroupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExerciseGroup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((exerciseGroup: HttpResponse<ExerciseGroup>) => {
          if (exerciseGroup.body) {
            return of(exerciseGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ExerciseGroup());
  }
}

export const exerciseGroupRoute: Routes = [
  {
    path: '',
    component: ExerciseGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhipsterSampleApplicationApp.exerciseGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExerciseGroupDetailComponent,
    resolve: {
      exerciseGroup: ExerciseGroupResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.exerciseGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExerciseGroupUpdateComponent,
    resolve: {
      exerciseGroup: ExerciseGroupResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.exerciseGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExerciseGroupUpdateComponent,
    resolve: {
      exerciseGroup: ExerciseGroupResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.exerciseGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
