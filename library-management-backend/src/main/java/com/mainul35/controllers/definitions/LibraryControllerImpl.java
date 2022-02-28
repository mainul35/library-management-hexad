package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.request.ReturningBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import com.mainul35.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class LibraryControllerImpl implements Library{

    private final LibraryService libraryService;

    @Autowired
    public LibraryControllerImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public ResponseEntity<LibraryStatus> getBooks() {
        return ResponseEntity.ok(libraryService.getLibraryStatus());
    }

    @Override
    public ResponseEntity<LibraryStatus> addBooks(ReturningBooks returningBooks) {
        return ResponseEntity.ok(libraryService.addAllBooks(returningBooks.getBooks()));
    }

    @Override
    public ResponseEntity<LibraryStatus> borrow(BorrowingBooks borrowingBooks) {
        return ResponseEntity.ok(libraryService.borrow(borrowingBooks));
    }

    @Override
    public ResponseEntity<LibraryStatus> toReturn(ReturningBooks returningBooks) {
        return ResponseEntity.ok(libraryService.returnAll(returningBooks.getBooks()));
    }
}
