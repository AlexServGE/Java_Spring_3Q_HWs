package ru.gb.springbootlesson3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private long readerId;
  @Column
  private long bookId;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime time;

  public Issue(long readerId, long bookId) {
    this.readerId = readerId;
    this.bookId = bookId;
    this.time = LocalDateTime.now();
  }
}
