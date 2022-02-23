package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequestMapping("/books")
public class LibraryControllerImpl implements Library{

    private final LibraryService libraryService;


    @Autowired
    public LibraryControllerImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @Override
    public ResponseEntity<?> getSimilarWords(BorrowingBooks borrowingBooks) {
        return null;
    }
}
