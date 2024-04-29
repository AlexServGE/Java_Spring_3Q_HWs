package org.example;

import com.example.timerAOPstarter.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.AspectIssueController.Count;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
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
    } catch (RuntimeException e){
      return ResponseEntity.status(409).build();
    }
  }

  @GetMapping("/{id}")
  @Timer
  public ResponseEntity<Issue> getIssueById(@PathVariable long id) {
    log.info("Поступил запрос на получение выписки книги: issueId={}"
            , id);

    try {
      return ResponseEntity.status(HttpStatus.OK).body(service.getIssueById(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/reader") //не передается в параметр метода
  public ResponseEntity<List<Issue>> getIssuesByReaderId(@RequestParam long id){ //название аргумента String name должно полностью соответствовать значению ключа name=Костя, передаваемого от пользователя
    return new ResponseEntity<>(service.getIssuesByReaderId(id), HttpStatus.OK);
  }

  @GetMapping("/issues")
  public ResponseEntity<ArrayList<ArrayList<String>>> getIssuesByReaderId(){
    return new ResponseEntity<>(service.getIssuesWithDescriptions(), HttpStatus.OK);
  }




//  @ExceptionHandler(RuntimeException.class)
//  public ResponseEntity<String> dropReaderRequestForBook(RuntimeException ex) {
//    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
//  }
//  @ExceptionHandler(NoSuchElementException.class)
//  public ResponseEntity<String> noAuthorityNoBook(NoSuchElementException ex) {
//    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//  }

}
