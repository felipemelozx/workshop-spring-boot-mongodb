package com.felipemelozx.workshopmongo.resources;

import com.felipemelozx.workshopmongo.DTO.UserDTO;
import com.felipemelozx.workshopmongo.domain.Post;
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

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping(value="/{id}/post")
    public ResponseEntity<List<Post>> findPost(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPostList());
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO){
        User user = service.fromDTO(userDTO);
        service.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<User> update(@RequestBody UserDTO userDTO, @PathVariable String id){
        User user = service.fromDTO(userDTO);
        user.setId(id);
        service.update(user);
        return  ResponseEntity.noContent().build();
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delteByid(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}