package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.BookService;
import ru.gb.springbootlesson3.services.IssueService;
import ru.gb.springbootlesson3.services.ReaderService;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями)
 * добавить следующие рерурсы,
 * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
 * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
 * 1.2 /ui/reader - аналогично 1.1
 * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы
 * (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
 * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и
 * перечислены книги, которые на руках у этого читателя
 */
@Controller
@RequestMapping("/ui")
public class UiController {

  @Autowired
  private BookService bookService;

  @Autowired
  private ReaderService readerService;

  @Autowired
  private IssueService issueService;

  @GetMapping("/books")
  public String getBooks(Model model) {
    model.addAttribute("books", bookService.findAll());
    return "books.html";
  }

  @GetMapping("/readers")
  public String getReader(Model model) {
    model.addAttribute("readers", readerService.findAll());
    return "readers.html";
  }

  @GetMapping("/issues")
  public String getIssues(Model model) {
    ArrayList<ArrayList<String>> issuesWithDesciptions = issueService.findAll()
            .stream()
            .map(issue -> {
              return Stream.of(String.valueOf(bookService.getBook(issue.getIdBook()))
                              , String.valueOf(readerService.getReader(issue.getIdReader()))
                              , issue.getTime().toString())
                      .collect(Collectors.toCollection(ArrayList::new));
            })
            .collect(Collectors.toCollection(ArrayList::new));
    ;
    model.addAttribute("issues", issuesWithDesciptions);
    return "issues.html";
  }
}
