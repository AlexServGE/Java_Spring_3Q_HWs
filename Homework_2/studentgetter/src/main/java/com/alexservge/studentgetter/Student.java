package com.alexservge.studentgetter;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Создать Класс Student c полями: идентификатор, имя, имя группы
 */

@Data
public class Student {
  private final long id;
  private final String name;
  private final String groupName;

}
