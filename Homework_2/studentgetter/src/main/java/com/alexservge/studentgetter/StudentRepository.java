package com.alexservge.studentgetter;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Data
public class StudentRepository {
  private final List<Student> repository;

  public StudentRepository() {
    this.repository = this.getTestList();
  }

  private ArrayList<Student> getTestList() {
    return new ArrayList<>(List.of(
            new Student(1, "Александр", "Ультрамарины"),
            new Student(2, "Фёдор", "Имперские кулаки"),
            new Student(3, "Алексей", "Ультрамарины"),
            new Student(4, "Константин", "Имперские кулаки"),
            new Student(5, "Александр", "Имперские кулаки")
    ));
  }

  public Student getById(long id) {
    return this.repository
            .stream()
            .filter(student -> student.getId() == id)
            .findAny()
            .orElseGet(null);
  }

  public List<Student> getAll() {
    return this.repository;
  }

  public List<Student> getStudentsWithSubString(String subString){
    return this.repository
            .stream()
            .filter(student -> student.getName().contains(subString))
            .collect(Collectors.toCollection(ArrayList::new));
  }

  public List<Student> getStudentsByGroupName(String groupNamee){
    return this.repository
            .stream()
            .filter(student -> student.getGroupName().equals(groupNamee))
            .collect(Collectors.toCollection(ArrayList::new));
  }



}
