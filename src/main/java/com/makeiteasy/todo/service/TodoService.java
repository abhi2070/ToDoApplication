package com.makeiteasy.todo.service;

import com.makeiteasy.todo.dto.TodoRequestDTO;
import com.makeiteasy.todo.dto.TodoResponceDTO;
import com.makeiteasy.todo.dto.UserTodosResponseDTO;
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

    public UserTodosResponseDTO getTodosByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<TodoResponceDTO> todos = todoRepository.findByUser(user).stream()
                .map(TodoMapper::toDTO)
                .collect(Collectors.toList());

        UserTodosResponseDTO response = new UserTodosResponseDTO();
        response.setUser(user.getName());
        response.setTodos(todos);

        return response;
    }


    public TodoResponceDTO updateTodo(Long id, TodoRequestDTO dto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUser().getUserID().equals(user.getUserID())) {
            throw new RuntimeException("Unauthorized access to delete todo");
        }

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setComplete(dto.isComplete());

        return TodoMapper.toDTO(todoRepository.save(todo));
    }

    public void deleteTodo(Long id, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUser().getUserID().equals(user.getUserID())) {
            throw new RuntimeException("Unauthorized access to delete todo");
        }

        todoRepository.delete(todo);
    }

}
