package com.felipemelozx.workshopmongo.resources;

import com.felipemelozx.workshopmongo.DTO.UserDTO;
import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO){
        User user = service.fromDTO(userDTO);
        service.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findByid(@PathVariable String id){
        UserDTO userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delteByid(@PathVariable String id){
       service.delete(id);
        return ResponseEntity.noContent().build();
    }
}