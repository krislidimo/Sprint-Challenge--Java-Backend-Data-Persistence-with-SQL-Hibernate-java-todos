package com.lambdaschool.javatodo.controllers;

import com.lambdaschool.javatodo.models.Todo;
import com.lambdaschool.javatodo.models.User;
import com.lambdaschool.javatodo.services.TodoService;
import com.lambdaschool.javatodo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    //Gets current user
    @GetMapping(value = "/mine", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }

    //Add new user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/newuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //Add new todo to User
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewTodoToUser(@Valid @RequestBody Todo todo, @PathVariable Long userid) throws URISyntaxException
    {
        Todo newtodo =todoService.saveToUser(todo, userid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newtodo.getTodoid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    //Delete user by id
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}