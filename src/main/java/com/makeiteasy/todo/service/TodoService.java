package com.makeiteasy.todo.service;

import com.makeiteasy.todo.dto.TodoRequestDTO;
import com.makeiteasy.todo.dto.TodoResponceDTO;
import com.makeiteasy.todo.mapper.TodoMapper;
import com.makeiteasy.todo.model.Todo;
import com.makeiteasy.todo.model.User;
import com.makeiteasy.todo.repo.TodoRepository;
import com.makeiteasy.todo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public TodoResponceDTO createTodo(TodoRequestDTO dto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Todo todo = TodoMapper.toEntity(dto);
        todo.setUser(user);

        Todo saved = todoRepository.save(todo);
        return TodoMapper.toDTO(saved);
    }

    public List<TodoResponceDTO> getTodosByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return todoRepository.findByUser(user).stream()
                .map(TodoMapper::toDTO)
                .collect(Collectors.toList());
    }

}
