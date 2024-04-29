package ru.gb.springbootlesson3.dtos;

import lombok.Data;

@Data
public class ReaderRequest {
  private long readerId;
  private String readerName;
}
