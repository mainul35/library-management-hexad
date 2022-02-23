package com.mainul35.dtos.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingBooks {
    List<String> bookIds;
}
