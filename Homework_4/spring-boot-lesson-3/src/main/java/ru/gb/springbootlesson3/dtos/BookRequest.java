package ru.gb.springbootlesson3.dtos;

import lombok.Data;

@Data
public class BookRequest {
  private long bookId;
  private String bookName;
}
