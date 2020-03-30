import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExerciseGroup } from 'app/shared/model/exercise-group.model';

type EntityResponseType = HttpResponse<IExerciseGroup>;
type EntityArrayResponseType = HttpResponse<IExerciseGroup[]>;

@Injectable({ providedIn: 'root' })
export class ExerciseGroupService {
  public resourceUrl = SERVER_API_URL + 'api/exercise-groups';

  constructor(protected http: HttpClient) {}

  create(exerciseGroup: IExerciseGroup): Observable<EntityResponseType> {
    return this.http.post<IExerciseGroup>(this.resourceUrl, exerciseGroup, { observe: 'response' });
  }

  update(exerciseGroup: IExerciseGroup): Observable<EntityResponseType> {
    return this.http.put<IExerciseGroup>(this.resourceUrl, exerciseGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExerciseGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExerciseGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
