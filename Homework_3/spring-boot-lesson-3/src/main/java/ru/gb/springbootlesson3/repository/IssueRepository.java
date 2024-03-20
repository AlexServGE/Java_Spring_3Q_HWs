package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue){
        list.add(issue);
    }

    public Issue findIssueByIssueId(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Issue findIssueByReaderId(long readerId){
        return list.stream().filter(e -> e.getIdReader() == readerId)
                .findFirst()
                .orElse(null);
    }

    public List<Issue> getIssueListByReaderId(long readerId){
        return list.stream().filter(e->e.getIdReader()==readerId).collect(Collectors.toCollection(ArrayList::new));
    }

}
