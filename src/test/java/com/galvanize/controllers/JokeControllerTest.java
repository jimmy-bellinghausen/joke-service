package com.galvanize.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import com.galvanize.services.JokeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class JokeControllerTest {
    @Autowired
    MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    JokeService jokeService;

    @Test
    public void postJoke() throws Exception {
        Joke expected = new Joke();
        String json = mapper.writeValueAsString(expected);
        expected.setJokeId(1L);
        when(jokeService.postJoke(any(Joke.class))).thenReturn(expected);
        mvc.perform(post("/api/joke").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jokeId").value(expected.getJokeId()));
    }

    @Test
    public void getAllJokes() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeService.getAllJokes()).thenReturn(expectedJokes);
        mvc.perform(get("/api/joke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jokeId").value(expected.getJokeId()));
    }

    @Test
    public void getAllJokesContaining() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        expected.setJoke("Hi hungry, I'm dad!");
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeService.getAllJokesContaining(anyString())).thenReturn(expectedJokes);
        mvc.perform(get("/api/joke/containing?contains=dad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].joke").value(expected.getJoke()))
                .andExpect(jsonPath("$[0].jokeId").value(expected.getJokeId()));
    }

    @Test
    public void getAllJokesContainingCategory() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        expected.setJoke("Hi hungry, I'm dad!");
        expected.setCategory(Category.DADJOKES);
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeService.getAllJokesContaining(anyString(), any(Category.class))).thenReturn(expectedJokes);
        mvc.perform(get("/api/joke/containing?contains=dad&searchCategory=DADJOKES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].joke").value(expected.getJoke()))
                .andExpect(jsonPath("$[0].jokeId").value(expected.getJokeId()))
                .andExpect(jsonPath("$[0].category").value(expected.getCategory().toString()));
    }

    @Test
    public void getAllJokesByCategory() throws Exception{
        Joke expected = new Joke();
        expected.setJokeId(1L);
        expected.setJoke("Hi hungry, I'm dad!");
        expected.setCategory(Category.DADJOKES);
        ArrayList<Joke> expectedJokes = new ArrayList<>();
        expectedJokes.add(expected);
        when(jokeService.getAllJokesByCategory(any(Category.class))).thenReturn(expectedJokes);
        mvc.perform(get("/api/joke/category?category=DADJOKES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].joke").value(expected.getJoke()))
                .andExpect(jsonPath("$[0].jokeId").value(expected.getJokeId()))
                .andExpect(jsonPath("$[0].category").value(expected.getCategory().toString()));
    }

}