package com.galvanize.services;

import com.galvanize.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
    JokeRepository repository;

    public JokeService(JokeRepository repository){
        this.repository=repository;
    }

    public Joke postJoke(Joke input) {
        return repository.save(input);
    }
}
