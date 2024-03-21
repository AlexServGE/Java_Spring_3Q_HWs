package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.dtos.BookRequest;
import ru.gb.springbootlesson3.dtos.IssueRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {
  private final BookRepository bookRepository;

  public Book getBook(long id){
    if (bookRepository.findById(id) == null){
      log.info("Не удалось найти книгу с id " + id);
      throw new NoSuchElementException("Не удалось найти книгу с id " + id);
    }

    return bookRepository.findById(id);
  }

  public List<Book> findAll(){
    return bookRepository.getList();
  }

  public void deleteBook(long id){
    if (bookRepository.findById(id) == null){
      log.info("Не удалось найти книгу с id " + id);
      throw new NoSuchElementException("Не удалось найти книгу с id " + id);
    }
    bookRepository.deleteById(id);
  }

  public Book createBook(BookRequest request){
    Book newBook = new Book(request.getBookName());
    bookRepository.addBook(newBook);
    return newBook;
  }
}
