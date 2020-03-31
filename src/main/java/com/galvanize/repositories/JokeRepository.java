package com.galvanize.repositories;

import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
    List<Joke> findAllByJokeContaining(String partOfJoke);
    List<Joke> findAllByCategory(Category category);
}
