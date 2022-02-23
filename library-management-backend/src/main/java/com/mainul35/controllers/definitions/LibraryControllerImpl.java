package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import com.mainul35.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryControllerImpl implements Library{

    private final LibraryService libraryService;


    @Autowired
    public LibraryControllerImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @Override
    public ResponseEntity<LibraryStatus> borrow(BorrowingBooks borrowingBooks) {
        return ResponseEntity.ok(libraryService.borrow(borrowingBooks));
    }
}
