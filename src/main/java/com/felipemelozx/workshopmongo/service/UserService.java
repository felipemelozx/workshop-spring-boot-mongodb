package com.felipemelozx.workshopmongo.service;

import com.felipemelozx.workshopmongo.DTO.UserDTO;
import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.repository.UserRepository;
import com.felipemelozx.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(),objDTO.getName(), objDTO.getEmail());
    }

    public User createUser(User user){
        return userRepository.insert(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        Optional<User> newObj = userRepository.findById(obj.getId());
        updateData(newObj, obj );
        return userRepository.save(newObj.orElseThrow());
    }

    private void updateData(Optional<User> newObj, User obj) {
        newObj.orElseThrow().setName(obj.getName());
        newObj.orElseThrow().setName(obj.getName());
    }
}
