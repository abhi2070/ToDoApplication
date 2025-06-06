package com.makeiteasy.todo.service;

import com.makeiteasy.todo.model.User;
import com.makeiteasy.todo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserDetailById(long id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers(){
        List<User> users=userRepository.findAll();
        return  users;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(long id, User user){
        User existingUser = userRepository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(long id){
        User existingUser = userRepository.findById(id).get();
        userRepository.delete(existingUser);
    }
}
