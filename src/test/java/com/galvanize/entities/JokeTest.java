package com.galvanize.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JokeTest {
    @Test
    public void updateTest(){
        Joke actual = new Joke(Category.KIDJOKES, "Dad joke");
        Joke update = new Joke();
        update.setCategory(Category.DADJOKES);
        Joke expected = new Joke(update.getCategory(), actual.getJoke());
        actual.update(update);
        assertEquals(expected, actual);
    }
}