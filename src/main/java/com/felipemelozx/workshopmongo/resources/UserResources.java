package com.felipemelozx.workshopmongo.resources;

import com.felipemelozx.workshopmongo.DTO.UserDTO;
import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public void create(@RequestBody User user){
        User user1 = new User(user.getName(), user.getEmail());
        service.createUser(user);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findByid(@PathVariable String id){
        UserDTO userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }
}