package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.dtos.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
  private final BookRepository bookRepository;
  private final IssueRepository issueRepository;
  private final ReaderRepository readerRepository;

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
      throw new RuntimeException("У данного читателя имеется книга на руках под номер book id"+ issueRepository.findIssueByReaderId(request.getReaderId()));
    }

    Issue issue = new Issue(request.getReaderId(), request.getBookId());
    issueRepository.createIssue(issue);
    return issue;
  }

  public List<Issue> findAll() {
    return issueRepository.getList();
  }

  public Issue getIssueById(long id) {
    if (issueRepository.findIssueByIssueId(id) == null) {
      log.info("Не удалось найти выписку с id " + id);
      throw new NoSuchElementException("Не удалось найти выписку с id " + id);
    }
    return issueRepository.findIssueByIssueId(id);
  }
}
