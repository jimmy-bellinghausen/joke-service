package com.galvanize.services;

import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JokeService {
    JokeRepository repository;

    public JokeService(JokeRepository repository){
        this.repository=repository;
    }

    public Joke postJoke(Joke input) {
        return repository.save(input);
    }

    public List<Joke> getAllJokes() {
        return repository.findAll();
    }

    public List<Joke> getAllJokesContaining(String input) {
        return repository.findAllByJokeContaining(input);
    }

    public List<Joke> getAllJokesContaining(String input, Category category) {
        List<Joke> jokesContainingString = repository.findAllByJokeContaining(input);
        List<Joke> returnList = new ArrayList<>();
        jokesContainingString.forEach((Joke joke) ->{
            if(joke.getJoke().contains(input)){returnList.add(joke);}
        });
        return returnList;
    }

    public List<Joke> getAllJokesByCategory(Category category) {
        return repository.findAllByCategory(category);
    }
}
