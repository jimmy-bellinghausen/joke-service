package com.galvanize.services;

import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import com.galvanize.repositories.JokeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JokeServiceTest {
    @MockBean
    JokeRepository jokeRepository;

    @Test
    public void postJoke(){
        JokeService service = new JokeService(jokeRepository);
        Joke input = new Joke(Category.DADJOKES,"Hi hungry, I'm dad!");
        Joke expected = new Joke(1L, Category.DADJOKES,"Hi hungry, I'm dad!");
        when(jokeRepository.save(any(Joke.class))).thenReturn(expected);
        assertEquals(expected,service.postJoke(input));
    }

    @Test
    public void getAllJokes(){
        JokeService service = new JokeService(jokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Hi hungry, I'm dad!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeRepository.findAll()).thenReturn(expectedJokes);
        assertEquals(expectedJokes, service.getAllJokes());
    }

    @Test
    public void getAllJokesContaining(){
        JokeService service = new JokeService(jokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Hi hungry, I'm dad!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeRepository.findAllByJokeContaining(anyString())).thenReturn(expectedJokes);
        assertEquals(expectedJokes, service.getAllJokesContaining("dad"));
    }

    @Test
    public void getAllJokesContainingCategory(){
        JokeService service = new JokeService(jokeRepository);
        Joke expected = new Joke(1L, Category.DADJOKES,"Hi hungry, I'm dad!");
        Joke knockKnockJoke = new Joke(1L, Category.KNOCKKNOCK,"Knock knock, orange you glad I didn't say banana?");
        ArrayList<Joke> allJokes = new ArrayList<>();
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        allJokes.add(expected);
        allJokes.add(knockKnockJoke);
        expectedJokes.add(expected);
        when(jokeRepository.findAllByJokeContaining(anyString())).thenReturn(allJokes);
        assertEquals(expectedJokes, service.getAllJokesContaining("dad", Category.DADJOKES));
    }
}
