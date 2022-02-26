import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookModel} from "../models/bookModel";
import {environment} from "../../environments/environment";
import {ReturningBooks} from "../models/returningBooks";
import {LibraryStatus} from "../models/libraryStatus";
import {BorrowingBooks} from "../models/borrowingBooks";


@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  libraryStatus: LibraryStatus | null = new LibraryStatus();

  updateLibraryStatus(libraryStatus: LibraryStatus | null) {
    this.libraryStatus = libraryStatus;
  }

  getLibraryStatus (): Observable<HttpResponse<LibraryStatus>> {
    // @ts-ignore
    return this.httpClient.get<LibraryStatus>(environment.RESEARCH_SERVICE + '/books', {observe : 'response'});

  }

  // @ts-ignore
  constructor(private httpClient: HttpClient) { }

  addBook(returningBooks: ReturningBooks): Observable<HttpResponse<LibraryStatus>> {
    return this.httpClient.post<LibraryStatus>(environment.RESEARCH_SERVICE + '/books/add', returningBooks,{observe : 'response'});
  }

  borrowBook(borrowingBooks: BorrowingBooks): Observable<HttpResponse<LibraryStatus>> {
    return this.httpClient.post<LibraryStatus>(environment.RESEARCH_SERVICE + '/books/borrow', borrowingBooks,{observe : 'response'});
  }
}
