package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@CrossOrigin(origins = "${cross.origin}")
@RestController("/books")
public interface Library {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Book> getFrequencies();

    @PostMapping(value = "/borrow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSimilarWords(@Valid @RequestBody BorrowingBooks borrowingBooks);

}
