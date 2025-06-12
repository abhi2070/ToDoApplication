package com.makeiteasy.todo.controller;

import com.makeiteasy.todo.dto.TodoRequestDTO;
import com.makeiteasy.todo.dto.TodoResponceDTO;
import com.makeiteasy.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

        @Autowired
        private TodoService todoService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TodoResponceDTO>> getTodos(Authentication authentication) {
        String userEmail = authentication.getName();
        List<TodoResponceDTO> todos = todoService.getTodosByUser(userEmail);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoResponceDTO> createTodo(@RequestBody TodoRequestDTO dto,
                                                      Authentication authentication) {
        String userEmail = authentication.getName(); // this comes from JWT token
        TodoResponceDTO createdTodo = todoService.createTodo(dto, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

}
