package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.dtos.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

  private final IssueService service;


  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
    log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
            , issueRequest.getReaderId(), issueRequest.getBookId());

    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Issue> getIssueById(@PathVariable long id) {
    log.info("Поступил запрос на получение выписки книги: issueId={}"
            , id);

    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.getIssueById(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> dropReaderRequestForBook(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> noAuthorityNoBook(NoSuchElementException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}
