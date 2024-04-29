package ru.gb.springbootlesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

  Issue findIssueById(long id);

  Issue findIssueByReaderId(long readerId);

  List<Issue> findIssuesByReaderId(long readerId);


}
