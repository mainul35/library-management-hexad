package com.mainul35.dtos.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LibraryStatus {
    private List<Book> borrowedBooks;
    private List<Book> remainingBooks;

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = new ArrayList<>(borrowedBooks);
    }

    public void setRemainingBooks(List<Book> remainingBooks) {
        this.remainingBooks = new ArrayList<>(remainingBooks);
    }
}
