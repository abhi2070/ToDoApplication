package com.makeiteasy.todo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Name can't be empty")
    private String name;

    @Column(unique = true)
    private String userName;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank
    private String password;
    private String role;
}
