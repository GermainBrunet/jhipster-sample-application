import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILanguageCode } from 'app/shared/model/language-code.model';

type EntityResponseType = HttpResponse<ILanguageCode>;
type EntityArrayResponseType = HttpResponse<ILanguageCode[]>;

@Injectable({ providedIn: 'root' })
export class LanguageCodeService {
  public resourceUrl = SERVER_API_URL + 'api/language-codes';

  constructor(protected http: HttpClient) {}

  create(languageCode: ILanguageCode): Observable<EntityResponseType> {
    return this.http.post<ILanguageCode>(this.resourceUrl, languageCode, { observe: 'response' });
  }

  update(languageCode: ILanguageCode): Observable<EntityResponseType> {
    return this.http.put<ILanguageCode>(this.resourceUrl, languageCode, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILanguageCode>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILanguageCode[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
