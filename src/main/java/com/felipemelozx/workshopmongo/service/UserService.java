package com.felipemelozx.workshopmongo.service;

import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
