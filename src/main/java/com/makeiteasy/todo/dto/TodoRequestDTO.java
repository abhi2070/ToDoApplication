package com.makeiteasy.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequestDTO {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private boolean complete;
    private long userID;
}
