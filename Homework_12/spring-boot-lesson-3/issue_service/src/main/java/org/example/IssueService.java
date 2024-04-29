package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.AspectIssueController.Count;
import org.example.BookProvider.BookProvider;
import org.example.ReaderProvider.ReaderProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
  private final IssueRepository issueRepository;
  private final BookProvider bookProvider;
  private final ReaderProvider readerProvider;

  @EventListener(ContextRefreshedEvent.class)
  public void generateIssues() {
    issueRepository.save(new Issue(1, 2));
    issueRepository.save(new Issue(2, 2));
    issueRepository.save(new Issue(1, 2));
  }

  @Count
  public Issue createIssue(IssueRequest request) {
    if (bookProvider.getBookById(request.getBookId()) == null) {
      log.info("Не удалось найти книгу с id " + request.getBookId());
      throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
    }
    if (readerProvider.getReaderById(request.getReaderId()) == null) {
      log.info("Не удалось найти читателя с id " + request.getReaderId());
      throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
    }
    if ( issueRepository.findIssueByReaderId(request.getReaderId())!= null) {
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

  public List<Issue> getIssuesByReaderId(long id){
    return issueRepository.findIssuesByReaderId(id);
  }

  public ArrayList<ArrayList<String>> getIssuesWithDescriptions() {
    return this.findAll()
            .stream()
            .map(issue -> {
              return Stream.of(String.valueOf(bookProvider.getBookById(issue.getBookId()))
                              , String.valueOf(readerProvider.getReaderById(issue.getReaderId()))
                              , issue.getTime().toString())
                      .collect(Collectors.toCollection(ArrayList::new));
            })
            .collect(Collectors.toCollection(ArrayList::new));
  }

}
