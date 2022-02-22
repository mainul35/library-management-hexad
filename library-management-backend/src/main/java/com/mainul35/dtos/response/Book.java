package com.mainul35.dtos.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class Book implements Serializable {

    private String id;
    private String bookName;
    private String authorName;
    private String isbn;
}
