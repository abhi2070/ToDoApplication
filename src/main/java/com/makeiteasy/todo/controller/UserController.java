package com.makeiteasy.todo.controller;

import com.makeiteasy.todo.model.User;
import com.makeiteasy.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

   @GetMapping("/getUser/{id}")
   public User getUser(@PathVariable long id){
       return userService.getUserDetailById(id);
   }

   @GetMapping("/getUsers")
   public List<User> getUsers(){
       List<User> users=userService.getAllUsers();
       return  users;
   }

    @PostMapping("/newUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable long id){
       userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
