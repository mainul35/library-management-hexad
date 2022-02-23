package com.mainul35.dtos.response;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private String id;
    private String bookName;
    private String authorName;
    private String isbn;
}
