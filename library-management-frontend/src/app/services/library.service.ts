import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookModel} from "../models/bookModel";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  // @ts-ignore
  constructor(private httpClient: HttpClient) { }

  getBooks(): Observable<HttpResponse<BookModel>> {
    // @ts-ignore
    return this.httpClient.get<BookModel>(environment.RESEARCH_SERVICE + '/books', {observe : 'response'});
  }

}
