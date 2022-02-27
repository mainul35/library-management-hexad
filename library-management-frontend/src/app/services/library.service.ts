import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {BookModel} from "../models/bookModel";
import {environment} from "../../environments/environment";
import {ReturningBooks} from "../models/returningBooks";
import {LibraryStatus} from "../models/libraryStatus";
import {BorrowingBooks} from "../models/borrowingBooks";


@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  getLibraryStatus (): Observable<HttpResponse<LibraryStatus>> {
    // @ts-ignore
    return this.httpClient.get<LibraryStatus>(environment.RESEARCH_SERVICE + '/books', {observe : 'response'});

  }

  // @ts-ignore
  constructor(private httpClient: HttpClient) {
    // @ts-ignore
    this.librarySubject = new BehaviorSubject<LibraryStatus>();
  }

  addBook(returningBooks: ReturningBooks): Observable<HttpResponse<LibraryStatus>> {
    return this.httpClient.post<LibraryStatus>(environment.RESEARCH_SERVICE + '/books/add', returningBooks,{observe : 'response'});
  }

  borrowBook(borrowingBooks: BorrowingBooks): Observable<HttpResponse<LibraryStatus>> {
    return this.httpClient.post<LibraryStatus>(environment.RESEARCH_SERVICE + '/books/borrow', borrowingBooks,{observe : 'response'});
  }

  toReturn(returningBooks : ReturningBooks) : Observable<HttpResponse<LibraryStatus>> {
    return this.httpClient.post<LibraryStatus>(environment.RESEARCH_SERVICE + '/books/return', returningBooks,{observe : 'response'});
  }
}
