package ru.gb.springbootlesson3.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springbootlesson3.security.entity.DbUser;
import ru.gb.springbootlesson3.security.repository.credentialsUserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final credentialsUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("ищем " + username);
    DbUser person = repository.findDbUserByLogin(username).orElseThrow(() ->
            new UsernameNotFoundException("Пользователь " + username + " не найден"));
    System.out.println("нашли " + person);
    return new User(person.getLogin(), person.getPassword(), List.of(
            new SimpleGrantedAuthority(person.getRole())
    ));
  }

  @EventListener(ContextRefreshedEvent.class)
  public void generateDbCredentialsUsers() {
    DbUser user1 = new DbUser();
    user1.setLogin("paradox");
    user1.setPassword("qwerty");
    user1.setRole("user");
    repository.save(user1);

    DbUser admin1 = new DbUser();
    admin1.setLogin("admin");
    admin1.setPassword("admin");
    admin1.setRole("admin");
    repository.save(admin1);
  }

}
