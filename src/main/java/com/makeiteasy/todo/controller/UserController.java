package com.makeiteasy.todo.controller;

import com.makeiteasy.todo.model.User;
import com.makeiteasy.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

   @GetMapping("/getUser/{id}")
   public User getUser(@PathVariable long id){
       return userService.getUserDetailById(id);
   }

    @PostMapping("/newUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
