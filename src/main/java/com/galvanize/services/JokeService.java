package com.galvanize.services;

import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        List<Joke> jokesContainingString = getAllJokesContaining(input);
        List<Joke> returnList = new ArrayList<>();
        jokesContainingString.forEach((Joke joke) ->{
            if(joke.getCategory().equals(category)){returnList.add(joke);}
        });
        return returnList;
    }

    public List<Joke> getAllJokesByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    public Joke getRandomJoke() {
        List<Joke> jokes = getAllJokes();
        return jokes.get((int)(Math.random()*jokes.size()));
    }

    public Joke getRandomJoke(Category category) {
        List<Joke> jokes = getAllJokesByCategory(category);
        return jokes.get((int)(Math.random()*jokes.size()));
    }

    public Joke update(long jokeId, Joke updateJoke) {
        Joke preUpdateJoke = repository.findById(jokeId).orElse(null);
        if(preUpdateJoke==null)return null;
        preUpdateJoke.update(updateJoke);
        return preUpdateJoke;
    }

    public boolean deleteById(long jokeId) {
        return repository.deleteByJokeId(jokeId)==1;
    }
}
