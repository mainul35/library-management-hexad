import {Component, Input, OnInit} from '@angular/core';
import {LibraryStatus} from "../../models/libraryStatus";
import {BookModel} from "../../models/bookModel";
import {LibraryService} from "../../services/library.service";
import {ReturningBooks} from "../../models/returningBooks";
import {BorrowingBooks} from "../../models/borrowingBooks";
import {Router} from "@angular/router";

@Component({
  selector: 'app-show-books',
  templateUrl: './show-books.component.html',
  styleUrls: ['./show-books.component.css']
})
export class ShowBooksComponent implements OnInit {

  remainingBooks: BookModel[] | null = [];
  borrowedBooks: BookModel[] | null = [];

  type: string = '';
  message: string = '';
  display: boolean = false;
  timeout: any;

  constructor(private libraryService: LibraryService, private router: Router) {
  }

  ngOnInit(): void {
    this.libraryService.getLibraryStatus().subscribe(value => {
      // @ts-ignore
      this.remainingBooks = value.body.remainingBooks;
      // @ts-ignore
      this.borrowedBooks = value.body.borrowedBooks;
    }, error => {
      console.log(error)
    })
  }

  borrowBook(book: BookModel) {
    if (this.timeout !== null) {
      clearTimeout(this.timeout);
    }
    const borrowingBooks = new BorrowingBooks();
    if (book.id != null) {
      borrowingBooks.bookIds?.push(book.id);
    }
    this.libraryService.borrowBook(borrowingBooks)
      .subscribe(value => {
        // @ts-ignore

          this.borrowedBooks = value.body.borrowedBooks;
          // @ts-ignore
          this.remainingBooks = value.body.remainingBooks;
          this.router.navigateByUrl('/show-books');
          },
      error => {
        if (error.error.error === 'limit_reached_error') {
          this.message = error.error.message;
          this.type = 'danger'
          this.displayAction();
          this.reloadLibrary();
        } else if (error.error.error === 'duplicate_entry') {
          this.message = error.error.message;
          this.type = 'danger'
          this.displayAction();
          this.reloadLibrary();
        } else {
          this.message = 'Unknown error. Please report and issue to mainuls18@gmail.com';
          this.type = 'danger'
          this.displayAction();
        }
      });
  }

  returnBook(book: BookModel) {
    if (this.timeout !== null) {
      clearTimeout(this.timeout);
    }
    const returningBooks = new ReturningBooks();
    returningBooks.books = [book]
    this.libraryService.toReturn(returningBooks)
      .subscribe(value => {
          // @ts-ignore

          this.borrowedBooks = value.body.borrowedBooks;
          // @ts-ignore
          this.remainingBooks = value.body.remainingBooks;
          this.router.navigateByUrl('/show-books');
        },
      error => {
        console.log(error)
        if (error.error.error === 'limit_reached_error') {
          this.message = error.error.message;
          this.type = 'danger'
          this.displayAction();
          this.reloadLibrary();
        }
      });
  }

  reloadLibrary () {
    this.libraryService.getLibraryStatus().subscribe(value => {
      // @ts-ignore
      this.remainingBooks = value.body.remainingBooks;
      // @ts-ignore
      this.borrowedBooks = value.body.borrowedBooks;
      this.router.navigateByUrl('/show-books')
    }, error => {
      console.log(error)
    })
  }

  displayAction() {
    // @ts-ignore
    this.timeout = setTimeout(() => {
      this.display = false;
    }, 5000);
    this.display = true;
  }
}
