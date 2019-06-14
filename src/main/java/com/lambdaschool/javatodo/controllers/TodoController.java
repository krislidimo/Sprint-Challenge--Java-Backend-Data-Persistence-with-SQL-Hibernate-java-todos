package com.lambdaschool.javatodo.controllers;

import com.lambdaschool.javatodo.models.Todo;
import com.lambdaschool.javatodo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TodoController {
    @RestController
    @RequestMapping("/todos")
    public class UserController {

        @Autowired
        private TodoService todoService;

        //Update todo
        @PutMapping(value = "/todoid/{todoid}")
        public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long todoid)
        {
            todoService.update(updateTodo, todoid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
