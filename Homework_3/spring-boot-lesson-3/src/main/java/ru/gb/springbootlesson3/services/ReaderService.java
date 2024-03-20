package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.dtos.BookRequest;
import ru.gb.springbootlesson3.dtos.ReaderRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
/**
   * 2.2 В сервис читателя добавить ручку
   * GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
*/
@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Reader getReader(long id) {
    if (readerRepository.findById(id) == null) {
      log.info("Не удалось найти читателя с id " + id);
      throw new NoSuchElementException("Не удалось найти читателя с id " + id);
    }

    return readerRepository.findById(id);
  }

  public List<Issue> getIssueListByReaderId(long id) {
    return issueRepository.getIssueListByReaderId(id);
  }

  public void deleteReader(long id) {
    if (readerRepository.findById(id) == null) {
      log.info("Не удалось найти читателя с id " + id);
      throw new NoSuchElementException("Не удалось найти читателя с id " + id);
    }
    readerRepository.deleteById(id);
  }

  public Reader addReader(ReaderRequest request) {
    Reader newReader = new Reader(request.getReaderName());
    readerRepository.addReader(newReader);
    return newReader;
  }
}
