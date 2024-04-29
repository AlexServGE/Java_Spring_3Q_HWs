package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.dtos.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
  private final BookRepository bookRepository;
  private final IssueRepository issueRepository;
  private final ReaderRepository readerRepository;

  @EventListener(ContextRefreshedEvent.class)
  public void generateIssues() {
    issueRepository.save(new Issue(1, 2));
    issueRepository.save(new Issue(2, 2));
    issueRepository.save(new Issue(1, 2));
  }

  public Issue createIssue(IssueRequest request) {
    if (bookRepository.findById(request.getBookId()) == null) {
      log.info("Не удалось найти книгу с id " + request.getBookId());
      throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
    }
    if (readerRepository.findById(request.getReaderId()) == null) {
      log.info("Не удалось найти читателя с id " + request.getReaderId());
      throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
    }
    if (issueRepository.findIssueByReaderId(request.getReaderId()) != null) {
      log.info("У данного читателя имеется книга на руках" + request.getReaderId());
      throw new RuntimeException("У данного читателя имеется книга на руках под номер book id" + issueRepository.findIssueByReaderId(request.getReaderId()));
    }

    Issue issue = new Issue(request.getReaderId(), request.getBookId());
    issueRepository.save(issue);
    return issue;
  }

  public List<Issue> findAll() {
    return issueRepository.findAll();
  }

  public Issue getIssueById(long id) {
    if (issueRepository.findIssueById(id) == null) {
      log.info("Не удалось найти выписку с id " + id);
      throw new NoSuchElementException("Не удалось найти выписку с id " + id);
    }
    return issueRepository.findIssueById(id);
  }

  public ArrayList<ArrayList<String>> getIssueWithDescriptions() {
    return this.findAll()
            .stream()
            .map(issue -> {
              return Stream.of(String.valueOf(bookRepository.findById(issue.getBookId()))
                              , String.valueOf(readerRepository.findById(issue.getReaderId()))
                              , issue.getTime().toString())
                      .collect(Collectors.toCollection(ArrayList::new));
            })
            .collect(Collectors.toCollection(ArrayList::new));
  }

}
