import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LibraryComponent } from './components/library/library.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { AddBookComponent } from './components/add-book/add-book.component';
import { ShowBooksComponent } from './components/show-books/show-books.component';
import { BorrowBookComponent } from './components/borrow-book/borrow-book.component';
import { ReturnBookComponent } from './components/return-book/return-book.component';
import { AlertComponent } from './components/alert/alert.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LibraryComponent,
    NotFoundComponent,
    AddBookComponent,
    ShowBooksComponent,
    BorrowBookComponent,
    ReturnBookComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
