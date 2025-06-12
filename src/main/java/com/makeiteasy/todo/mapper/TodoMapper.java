package com.makeiteasy.todo.mapper;

import com.makeiteasy.todo.dto.TodoRequestDTO;
import com.makeiteasy.todo.dto.TodoResponceDTO;
import com.makeiteasy.todo.model.Todo;

public class TodoMapper {

    public static Todo toEntity(TodoRequestDTO todoRequestDTO){
        Todo todo=new Todo();
        todo.setTitle(todoRequestDTO.getTitle());
        todo.setDescription(todoRequestDTO.getDescription());
        todo.setComplete(todoRequestDTO.isComplete());
        return todo;
    }

    public static TodoResponceDTO toDTO(Todo todo){
        TodoResponceDTO todoResponceDTO = new TodoResponceDTO();
        todoResponceDTO.setName(todo.getUser().getName());
        todoResponceDTO.setTitle(todo.getTitle());
        todoResponceDTO.setDescription(todo.getDescription());
        todoResponceDTO.setComplete(todo.isComplete());

        return todoResponceDTO;
    }
}
