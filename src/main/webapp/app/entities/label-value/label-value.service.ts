import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILabelValue } from 'app/shared/model/label-value.model';

type EntityResponseType = HttpResponse<ILabelValue>;
type EntityArrayResponseType = HttpResponse<ILabelValue[]>;

@Injectable({ providedIn: 'root' })
export class LabelValueService {
  public resourceUrl = SERVER_API_URL + 'api/label-values';

  constructor(protected http: HttpClient) {}

  create(labelValue: ILabelValue): Observable<EntityResponseType> {
    return this.http.post<ILabelValue>(this.resourceUrl, labelValue, { observe: 'response' });
  }

  update(labelValue: ILabelValue): Observable<EntityResponseType> {
    return this.http.put<ILabelValue>(this.resourceUrl, labelValue, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILabelValue>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILabelValue[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
