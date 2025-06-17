package com.makeiteasy.todo.dto;

import lombok.Data;

@Data
public class TodoResponceDTO {
    private String title;
    private String description;
    private boolean complete;
}
