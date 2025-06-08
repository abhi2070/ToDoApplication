package com.makeiteasy.todo.mapper;

import com.makeiteasy.todo.dto.UserRequestDTO;
import com.makeiteasy.todo.dto.UserResponceDTO;
import com.makeiteasy.todo.model.User;

public class UserMapper {

    //controller(request user ki) -> service(ki user ko add krna hai) -> userRequestDTO(ki new user add krne ki request aayi hai) -> user(user me data set hoga)
    public static User toEntity(UserRequestDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    //controller(get ki request aayi) -> service -> userResponceDTO -> MAPPER -> toDTO method se data de dete hai
    public static UserResponceDTO toDTO(User user){
        UserResponceDTO dto=new UserResponceDTO();
        dto.setUserID(user.getUserID());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole());
        return dto;
    }
}
