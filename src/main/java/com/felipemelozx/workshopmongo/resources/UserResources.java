package com.felipemelozx.workshopmongo.resources;

import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public void create(@RequestBody User user){
        service.createUser(user);
    }
}