package ru.gb.springbootlesson3.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson3.security.entity.DbUser;

import java.util.Optional;

public interface credentialsUserRepository extends JpaRepository<DbUser, Long> {
  Optional<DbUser> findDbUserByLogin(String login);
}
