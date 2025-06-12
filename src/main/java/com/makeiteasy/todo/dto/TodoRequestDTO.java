package com.makeiteasy.todo.dto;

import lombok.Data;

@Data
public class TodoRequestDTO {
    private String title;
    private String description;
    private boolean complete;
    private long userID;
}
