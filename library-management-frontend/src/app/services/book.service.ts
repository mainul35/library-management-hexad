import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class BookService {

  // @ts-ignore
  constructor(private httpClient: HttpClient) { }
}
