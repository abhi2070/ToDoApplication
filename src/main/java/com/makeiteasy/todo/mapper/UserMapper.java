package com.makeiteasy.todo.mapper;

import com.makeiteasy.todo.dto.UserRequestDTO;
import com.makeiteasy.todo.dto.UserResponceDTO;
import com.makeiteasy.todo.model.User;

public class UserMapper {

public static User toEntity(UserRequestDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public static UserResponceDTO toDTO(User user){
        UserResponceDTO dto=new UserResponceDTO();
        dto.setUserID(user.getUserID());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
