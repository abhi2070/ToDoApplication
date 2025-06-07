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
    private String email;

//    public Long getUserID() {
//        return userID;
//    }
//
//    public void setUserID(Long userID) {
//        this.userID = userID;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
    public User(Long userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public User() {}

//    @Override
//    public String toString() {
//        return "User{" +
//                "userID=" + userID +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
