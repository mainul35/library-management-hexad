package com.mainul35.services;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import com.mainul35.exceptions.LimitReachedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    List<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
//        books = objectMapper
//                .readValue (
//                        resourceLoader.getResource ("classpath:books.json").getInputStream(),
//                        new TypeReference<ArrayList<Book>>() {
//                        }
//                );
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    public void addBooks(List<Book> newBooks) {
        books.addAll(newBooks);
    }

    public void setBooks(List<Book> newBooks) {
        this.books = newBooks;
    }

    public LibraryStatus borrow(BorrowingBooks borrowingBooks) {
        var libraryStatus = new LibraryStatus();
        libraryStatus.setRemainingBooks(books);
        libraryStatus.setBorrowedBooks(new ArrayList<>());
        books.stream()
                .filter(book -> borrowingBooks.getBookIds().contains(book.getId()))
                .map(book -> populateBorrowedBookList(libraryStatus, book)).count();
        return libraryStatus;
    }

    private Book populateBorrowedBookList(LibraryStatus libraryStatus, Book book) {
        libraryStatus.getBorrowedBooks().add(book);
        libraryStatus.getRemainingBooks().remove(book);
        if (libraryStatus.getBorrowedBooks().size() > 2) {
            throw new LimitReachedException("Maximum borrowing limit reached");
        }
        return book;
    }
}
