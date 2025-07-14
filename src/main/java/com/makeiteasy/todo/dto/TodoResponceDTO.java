package com.makeiteasy.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponceDTO {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private boolean complete;
}
