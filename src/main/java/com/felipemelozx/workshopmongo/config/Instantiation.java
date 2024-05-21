package com.felipemelozx.workshopmongo.config;

import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria souza", "mariasouza@gmail.com");
        User felipe = new User(null, "felipe souza", "felipesouza@gmail.com");
        User joao = new User(null, "Joao souza", "joaosouza@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,felipe,joao));
    }
}
