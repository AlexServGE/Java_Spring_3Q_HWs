package ru.gb.springbootlesson3.dtos;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
