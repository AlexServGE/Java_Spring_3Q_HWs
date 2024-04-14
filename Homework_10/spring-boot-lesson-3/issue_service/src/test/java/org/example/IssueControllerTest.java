package org.example;


import org.example.TestOptionsClasses.JUnitSpringBootBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;


public class IssueControllerTest extends JUnitSpringBootBase {

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  private IssueRepository issueRepository;


  @BeforeEach
  void generateIssues(){
    issueRepository.save(new Issue(1, 2));
    issueRepository.save(new Issue(2, 2));
    issueRepository.save(new Issue(1, 2));
  }

  @Test
  void getIssueById() {
    Issue issue1 = issueRepository.findIssueById(1);

    Issue testIssue = webTestClient.get()
            .uri("issue/"+issue1.getId())
            .exchange()
            .expectStatus().isOk()
            .expectBody(Issue.class)
            .returnResult()
            .getResponseBody();
    Assertions.assertNotNull(testIssue);
    Assertions.assertEquals(issue1.getId(),testIssue.getId());
    Assertions.assertEquals(issue1.getBookId(),testIssue.getBookId());
    Assertions.assertEquals(issue1.getReaderId(),testIssue.getReaderId());
    Assertions.assertEquals(issue1.getTime(),testIssue.getTime());
  }
}
