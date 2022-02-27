import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookModel} from "../../models/bookModel";
import {ReturningBooks} from "../../models/returningBooks";
// @ts-ignore
import { v4 as uuidv4 } from 'uuid';
import {LibraryService} from "../../services/library.service";
import {ShowBooksComponent} from "../show-books/show-books.component";
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  bookName?: string;
  authorName?: string;
  isbn?: string;
  type: string = '';
  message: string = '';
  display: boolean = false;

  constructor(private libraryService: LibraryService, private router: Router) { }

  ngOnInit(): void {
  }

  addBook() {
    let book = new BookModel();
    book.bookName = this.bookName
    book.isbn = this.isbn
    book.authorName = this.authorName
    book.id = uuidv4();

    const returningBooks = new ReturningBooks();
    returningBooks.books = [book];

    this.libraryService.addBook(returningBooks)
      .subscribe(value => {
        // @ts-ignore
        this.type = "success";
        this.message = "Note saved successfully"
        // @ts-ignore
        this.router.navigateByUrl('/show-books')
        this.displayAction();
      }, error => {
        this.type = 'danger'
        this.message = "Failed to save note. Check server logs"
        this.displayAction();
      });
  }

  displayAction() {
    // @ts-ignore
    setTimeout(() => {
      this.display = false;
    }, 5000);
    this.display = true;
  }
}
