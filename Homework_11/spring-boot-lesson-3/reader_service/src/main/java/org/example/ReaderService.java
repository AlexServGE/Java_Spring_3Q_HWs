package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.IssueProvider.IssueDto;
import org.example.IssueProvider.IssueProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
  private final IssueProvider issueProvider;

  @EventListener(ContextRefreshedEvent.class)
  public void generateReaders() {
    readerRepository.save(new Reader("Костя"));
    readerRepository.save(new Reader("Василий"));
    readerRepository.save(new Reader("Семен"));
  }

  public Reader getReader(long id) {
    if (readerRepository.findById(id) == null) {
      log.info("Не удалось найти читателя с id " + id);
      throw new NoSuchElementException("Не удалось найти читателя с id " + id);
    }

    return readerRepository.findById(id);
  }

  public List<Reader> findAll(){
    return readerRepository.findAll();
  }

  public List<IssueDto> getIssueListByReaderId(long id) {
    return issueProvider.getIssueById(id);
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
    readerRepository.save(newReader);
    return newReader;
  }
}
