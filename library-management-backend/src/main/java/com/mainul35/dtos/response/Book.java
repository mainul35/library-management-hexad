package com.mainul35.dtos.response;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @NotNull(message = "Book ID must not be null")
    @NotEmpty(message = "Book ID must not be empty")
    private String id;
    @NotEmpty(message = "Book name must not be empty")
    private String bookName;
    @NotEmpty(message = "Author name must not be empty")
    private String authorName;
    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;
}
