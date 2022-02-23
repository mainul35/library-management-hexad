package com.mainul35.services;

import com.mainul35.dtos.response.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    List<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    public void addBooks(List<Book> newBooks) {
        books.addAll(newBooks);
    }
}
