package org.example;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
