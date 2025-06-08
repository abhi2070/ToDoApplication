package com.makeiteasy.todo.service;

import com.makeiteasy.todo.dto.UserRequestDTO;
import com.makeiteasy.todo.dto.UserResponceDTO;
import com.makeiteasy.todo.mapper.UserMapper;
import com.makeiteasy.todo.model.User;
import com.makeiteasy.todo.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserResponceDTO> getUserDetailById(long id){
        Optional<User> user = userRepository.findById(id);
        return Optional.of(UserMapper.toDTO(user.get()));
    }

    public List<UserResponceDTO> getAllUsers(){
        List<User> users=userRepository.findAll();
        log.info("List of Users: "+users);
        return  users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    public UserResponceDTO createUser(UserRequestDTO userRequestDTO) throws IllegalArgumentException{
        if (userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new IllegalArgumentException("Email already exist: "+userRequestDTO.getEmail());
        }
        User user = UserMapper.toEntity(userRequestDTO);
        //Encoding you password before saving it
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public UserResponceDTO updateUser(long id, UserRequestDTO userRequestDTO){
        if (userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new IllegalArgumentException("Email already exist: "+userRequestDTO.getEmail());
        }

        User existingUser = userRepository.findById(id).get();
        existingUser.setName(userRequestDTO.getName());
        existingUser.setEmail(userRequestDTO.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toDTO(updatedUser);
    }

    public boolean deleteUser(long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
