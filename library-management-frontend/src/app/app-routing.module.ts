import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LibraryComponent} from "./components/library/library.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {AddBookComponent} from "./components/add-book/add-book.component";
import {ShowBooksComponent} from "./components/show-books/show-books.component";
import {BorrowBookComponent} from "./components/borrow-book/borrow-book.component";
import {ReturnBookComponent} from "./components/return-book/return-book.component";

const routes: Routes = [
  {path: '', component: ShowBooksComponent},
  {path: 'add-book', component: AddBookComponent},
  {path: 'show-books', component: ShowBooksComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
