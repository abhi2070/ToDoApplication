package com.makeiteasy.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
                            /*
                            * @Data // Generates getters, setters, toString, equals, hashCode
                            @NoArgsConstructor
                            @AllArgsConstructor

                            * */
@Entity
@Table(name = "User")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userID;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String role;

    public User(Long userID, String name, String userName, String email, String password, String role) {
        this.userID = userID;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {}

}