package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.request.ReturningBooks;
import com.mainul35.dtos.response.Book;
import com.mainul35.dtos.response.LibraryStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping("/books")
public interface Library {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LibraryStatus> getBooks();

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LibraryStatus> addBooks(@Valid @RequestBody ReturningBooks returningBooks);

    @PostMapping(value = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LibraryStatus> borrow(@RequestBody BorrowingBooks borrowingBooks);

    @PostMapping(value = "/return", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LibraryStatus> toReturn(@RequestBody ReturningBooks returningBooks);
}
