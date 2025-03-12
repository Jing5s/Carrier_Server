package org.example.carrier.domain.todo.domain.repository;

import org.example.carrier.domain.todo.domain.Todo;
import org.example.carrier.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, CustomTodoRepository {
    List<Todo> findAllByDateAndUser(LocalDate date, User user);
    Optional<Todo> findByIdAndUser(Long id, User user);
}
