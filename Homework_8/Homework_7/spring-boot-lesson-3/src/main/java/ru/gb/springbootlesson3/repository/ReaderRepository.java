package ru.gb.springbootlesson3.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
  Reader findById(long id);

  void deleteById(long id);
}
