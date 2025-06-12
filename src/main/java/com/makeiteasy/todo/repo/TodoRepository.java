package com.makeiteasy.todo.repo;

import com.makeiteasy.todo.model.Todo;
import com.makeiteasy.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserUserID(Long userId);
    List<Todo> findByUser(User user);
}
