package com.lambdaschool.javatodo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false, unique = true)
    private String description;
    private String datestarted;
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("todos")
    private User user;

    public Todo(String description, String datestarted, User user) {
        this.description = description;
        this.datestarted = datestarted;
        this.completed = false;
        this.user = user;
    }

    public Todo() {
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(String datestarted) {
        this.datestarted = datestarted;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
