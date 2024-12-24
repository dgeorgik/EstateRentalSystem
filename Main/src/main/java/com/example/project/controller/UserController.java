package com.example.project.controller;

import com.example.project.model.SubscriberGrpc;
import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.services.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public HttpEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        user.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        users.forEach(user ->
                user.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel())
        );

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<EntityModel<User>> getUserByParams(@RequestBody User user) {
        User foundUser = userService.getUserByNameAndEmail(
                user.getName(),
                user.getEmail()
        );
        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EntityModel<User> userModel = EntityModel.of(foundUser,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                        .getUserById(foundUser.getId())).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
