package com.mainul35.services;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import com.mainul35.exceptions.DuplicateEntryException;
import com.mainul35.exceptions.LimitReachedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibraryService {

    final LibraryStatus libraryStatus;

    public LibraryService() {
        libraryStatus = new LibraryStatus(new ArrayList<>(), new ArrayList<>());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.libraryStatus.getRemainingBooks());
    }

    public LibraryStatus getLibraryStatus() {
        return new LibraryStatus(libraryStatus.getBorrowedBooks(), libraryStatus.getRemainingBooks());
    }

    public void addBook(Book newBook) {
        var remainingBooks = this.libraryStatus.getRemainingBooks();
        var found = remainingBooks
                .stream().anyMatch(book -> book.getId().equals(newBook.getId()));
        if (!found) {
            remainingBooks.add(newBook);
        }
    }

    public LibraryStatus addAllBooks(List<Book> newBooks) {
        newBooks.forEach(this::addBook);
        return libraryStatus;
    }

    public LibraryStatus borrow(BorrowingBooks borrowingBooks) {
        if (borrowingBooks.getBookIds() == null) {
            throw new NoSuchElementException("No books to borrow");
        }
        var remainingBooks = new ArrayList<>(libraryStatus.getRemainingBooks());
        remainingBooks.stream()
                .filter(book -> borrowingBooks.getBookIds().contains(book.getId()))
                .map(this::populateBorrowedBookList).count();
        return libraryStatus;
    }

    private LibraryStatus populateBorrowedBookList(Book book) {
        if (libraryStatus.getBorrowedBooks().size() + 1 > 2) {
            throw new LimitReachedException("Maximum borrowing limit reached");
        }
        handleDuplicates(book);
        libraryStatus.getBorrowedBooks().add(book);
        libraryStatus.getRemainingBooks().remove(book);
        return libraryStatus;
    }

    private void handleDuplicates(Book book) {
        libraryStatus.getBorrowedBooks().stream().iterator().forEachRemaining(book1 -> {
            if (book1.getIsbn().equals(book.getIsbn())) {
                throw new DuplicateEntryException("Cannot add 2 books of same ISBN at a time");
            }
        });
    }

    public LibraryStatus returnOne(Book bookToReturn) {
        var libStat = new LibraryStatus(libraryStatus.getBorrowedBooks(), libraryStatus.getRemainingBooks());
        libStat.getBorrowedBooks().remove(bookToReturn);
        libStat.getRemainingBooks().add(bookToReturn);
        return libStat;
    }

    public LibraryStatus returnAll(List<Book> borrowedBooks) {
        if (borrowedBooks == null) {
            throw new NoSuchElementException("No books to return");
        }
        var libStat = new LibraryStatus(libraryStatus.getBorrowedBooks(), libraryStatus.getRemainingBooks());
        for (var book : borrowedBooks) {
            libStat.getBorrowedBooks().removeIf(book1 -> book1.getId().equals(book.getId()));
            libStat.getRemainingBooks().add(book);
        }
        return libStat;
    }
}
