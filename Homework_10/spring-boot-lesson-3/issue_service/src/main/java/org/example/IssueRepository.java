package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

  Issue findIssueById(long id);

  Issue findIssueByReaderId(long readerId);
  List<Issue> findIssuesByReaderId(long readerId);


}
