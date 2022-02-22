package com.mainul35.dtos.request;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class BorrowingBooks {
    List<String> bookIds = new ArrayList<>(2);
}
