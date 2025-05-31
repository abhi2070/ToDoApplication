package com.makeiteasy.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @GetMapping("/hello")
    public String hello(){
        System.out.println("Hello");
        return "Hello Abhijeet";
    }
}
