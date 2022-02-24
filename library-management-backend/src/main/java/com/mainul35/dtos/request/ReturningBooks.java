package com.mainul35.dtos.request;

import com.mainul35.dtos.response.Book;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturningBooks {
    private List<Book> books;
}
