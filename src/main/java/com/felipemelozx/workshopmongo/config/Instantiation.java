package com.felipemelozx.workshopmongo.config;

import com.felipemelozx.workshopmongo.DTO.AuthorDTO;
import com.felipemelozx.workshopmongo.domain.Post;
import com.felipemelozx.workshopmongo.domain.User;
import com.felipemelozx.workshopmongo.repository.PostRepository;
import com.felipemelozx.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();
        
        User maria = new User(null, "Maria souza", "mariasouza@gmail.com");
        User felipe = new User(null, "felipe souza", "felipesouza@gmail.com");
        User joao = new User(null, "Joao souza", "joaosouza@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,felipe,joao));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!",new AuthorDTO(maria) );
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPostList().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
