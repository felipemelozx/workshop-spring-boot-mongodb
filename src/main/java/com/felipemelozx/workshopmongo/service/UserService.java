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
    public UserDTO findById(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return new UserDTO(user.orElseThrow());
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
}
