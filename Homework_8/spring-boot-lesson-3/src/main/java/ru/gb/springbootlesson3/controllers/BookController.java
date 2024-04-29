package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.dtos.BookRequest;
import ru.gb.springbootlesson3.dtos.IssueRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.NoSuchElementException;

/**
 * 1.1 Реализовать контроллер по управлению книгами с ручками:
 * GET    /book/{id} - получить описание книги,
 * DELETE /book/{id} - удалить книгу,
 * POST   /book - создать книгу
 */
@Slf4j
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

  private final BookService service;

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBook(@PathVariable long id) {
    log.info("Поступил запрос на получение информации о книге: bookId={}"
            , id);

    try {
      return ResponseEntity.ok().body(service.getBook(id));
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBook(@PathVariable long id) {
    log.info("Поступил запрос на удаление книги: bookId={}"
            , id);

    try {
      service.deleteBook(id);
      return ResponseEntity.ok().body("Книга успешно удалена");
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping()
  public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
    log.info("Поступил запрос на созадние новой книги: bookName={}"
            , bookRequest.getBookName());

    try {
      return ResponseEntity.ok().body(service.createBook(bookRequest));
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

}
