package com.alexservge.studentgetter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 3.1 GET /student/{id} - получить студента по ID
 * * 3.2 GET /student - получить всех студентов
 * * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
 * * 3.4 GET /group/{groupName}/student - получить всех студентов группы
 */
@RestController
@RequestMapping("student")
public class StudentController {

  final StudentRepository studentRepository;

  @Autowired
  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }


  @GetMapping("{id}")
  public Student getById(@PathVariable long id){
    return studentRepository.getById(id);
  }

  @GetMapping()
  public List<Student> getAll(){
    return studentRepository.getAll();
  }

  @GetMapping("search")
  public List<Student> getStudentsWithSubString(@RequestParam String name){
    return studentRepository.getStudentsWithSubString(name);
  }

  @GetMapping("/group/{groupName}/student")
  public List<Student> getStudentsByGroupName(@PathVariable String groupName){
    return studentRepository.getStudentsByGroupName(groupName);
  }
}
