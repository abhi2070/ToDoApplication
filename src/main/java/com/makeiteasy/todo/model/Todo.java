package com.makeiteasy.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Todo")
@Getter @Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

}
