package ru.gb.springbootlesson3.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Data
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public IssueRepository() {
        list.add(new Issue(1,2));
        list.add(new Issue(2,2));
        list.add(new Issue(1,2));
    }

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
