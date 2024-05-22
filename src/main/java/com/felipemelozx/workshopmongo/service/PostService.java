package com.felipemelozx.workshopmongo.service;

import com.felipemelozx.workshopmongo.domain.Post;
import com.felipemelozx.workshopmongo.repository.PostRepository;
import com.felipemelozx.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public Post findById(String id) {
        try {
            Optional<Post> post = postRepository.findById(id);
            return post.orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    public void delete(String id){
        postRepository.deleteById(id);
    }
}
