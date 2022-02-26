import { Component, OnInit } from '@angular/core';
import {BookModel} from "../../models/bookModel";
import {ReturningBooks} from "../../models/returningBooks";
// @ts-ignore
import { v4 as uuidv4 } from 'uuid';
import {LibraryService} from "../../services/library.service";

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  bookName?: string;
  authorName?: string;
  isbn?: string;

  constructor(private libraryService: LibraryService) { }

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
        this.libraryService.updateLibraryStatus(value.body);
      });
  }
}
