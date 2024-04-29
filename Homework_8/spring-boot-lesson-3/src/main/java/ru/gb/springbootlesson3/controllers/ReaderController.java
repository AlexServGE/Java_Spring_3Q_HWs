package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.dtos.BookRequest;
import ru.gb.springbootlesson3.dtos.ReaderRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 1.1 Реализовать контроллер по управлению книгами с ручками:
 * GET    /reader/{id} - получить описание книги,
 * DELETE /reader/{id} - удалить книгу,
 * POST   /reader - создать книгу
 */
/**
 * 2.2 В сервис читателя добавить ручку
 * GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
 */
@Slf4j
@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReaderController {

  private final ReaderService service;

  @GetMapping("/{id}")
  public ResponseEntity<Reader> getReader(@PathVariable long id) {
    log.info("Поступил запрос на получение информации о читателе: readerId={}"
            , service.getReader(id));

    try {
      return ResponseEntity.ok().body(service.getReader(id));
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}/issue")
  public ResponseEntity<List<Issue>> getIssueListByReaderId(@PathVariable long id) {
    log.info("Поступил запрос на получение информации о выписках читателя: readerId={}"
            , service.getReader(id));

    try {
      return ResponseEntity.ok().body(service.getIssueListByReaderId(id));
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteReader(@PathVariable long id) {
    log.info("Поступил запрос на удаление читателя: bookId={}"
            , id);

    try {
      service.deleteReader(id);
      return ResponseEntity.ok().body("Читатель успешно удален");
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping()
  public ResponseEntity<Reader> addReader(@RequestBody ReaderRequest readerRequest) {
    log.info("Поступил запрос на добавление нового читателя: readerName={}"
            , readerRequest.getReaderName());

    try {
      return ResponseEntity.ok().body(service.addReader(readerRequest));
    } catch (NoSuchElementException e){
      return ResponseEntity.notFound().build();
    }
  }

}
