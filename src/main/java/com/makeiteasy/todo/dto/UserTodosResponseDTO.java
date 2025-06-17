package com.makeiteasy.todo.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserTodosResponseDTO {
    private String user;
    private List<TodoResponceDTO> todos;
}
