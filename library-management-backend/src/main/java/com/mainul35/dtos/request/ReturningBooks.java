package com.mainul35.dtos.request;

import com.mainul35.dtos.response.Book;
import lombok.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturningBooks {
    @Valid
    private List<Book> books;
}
