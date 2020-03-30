import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILanguageValue } from 'app/shared/model/language-value.model';

type EntityResponseType = HttpResponse<ILanguageValue>;
type EntityArrayResponseType = HttpResponse<ILanguageValue[]>;

@Injectable({ providedIn: 'root' })
export class LanguageValueService {
  public resourceUrl = SERVER_API_URL + 'api/language-values';

  constructor(protected http: HttpClient) {}

  create(languageValue: ILanguageValue): Observable<EntityResponseType> {
    return this.http.post<ILanguageValue>(this.resourceUrl, languageValue, { observe: 'response' });
  }

  update(languageValue: ILanguageValue): Observable<EntityResponseType> {
    return this.http.put<ILanguageValue>(this.resourceUrl, languageValue, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILanguageValue>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILanguageValue[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
