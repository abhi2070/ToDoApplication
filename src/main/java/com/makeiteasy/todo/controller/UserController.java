package com.makeiteasy.todo.controller;

import com.makeiteasy.todo.dto.UserRequestDTO;
import com.makeiteasy.todo.dto.UserResponceDTO;
import com.makeiteasy.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

   @GetMapping("/getUser/{id}")
   public ResponseEntity<?> getUser(@PathVariable long id){
       Optional<UserResponceDTO> user = userService.getUserDetailById(id);
       if (user.isPresent()){
           return ResponseEntity.status(HttpStatus.OK).body(user);
       }else {
           return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Not Found");
       }
   }

   @GetMapping("/getUsers")
   public ResponseEntity<List<UserResponceDTO>> getUsers(){
       List<UserResponceDTO> users=userService.getAllUsers();
       if (users.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.status(HttpStatus.OK).body(users);
   }

    @PostMapping("/newUser")
    public ResponseEntity<UserResponceDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
       try{
           UserResponceDTO newUser = userService.createUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
       }catch (Exception e){
           log.error("Failed to create new User "+e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody @Valid UserRequestDTO userRequestDTO){
        try{
            UserResponceDTO update = userService.updateUser(id, userRequestDTO);;
            return ResponseEntity.ok(update);
        }catch (Exception e){
            log.info("Failed to update User "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable long id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.ok("User Deleted Successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found with this Id: "+id);
        }
    }
}
