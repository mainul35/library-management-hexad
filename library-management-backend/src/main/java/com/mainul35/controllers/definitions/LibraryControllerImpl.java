package com.mainul35.controllers.definitions;

import com.mainul35.dtos.request.BorrowingBooks;
import com.mainul35.dtos.response.Book;
import org.springframework.http.ResponseEntity;

public class LibraryControllerImpl implements Library{

    @Override
    public ResponseEntity<Book> getFrequencies() {
        return null;
    }

    @Override
    public ResponseEntity<?> getSimilarWords(BorrowingBooks borrowingBooks) {
        return null;
    }
}
