package com.galvanize.controllers;

import com.galvanize.entities.Category;
import com.galvanize.entities.Joke;
import com.galvanize.services.JokeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joke")
public class JokeController {
    JokeService service;

    public JokeController(JokeService service){
        this.service=service;
    }

    @PostMapping
    public Joke postJoke(@RequestBody Joke input){
        return service.postJoke(input);
    }

    @GetMapping
    public List<Joke> getAllJokes(){
        return service.getAllJokes();
    }

    @GetMapping("/containing")
    public List<Joke> getAllJokesContaining(@RequestParam String contains, @RequestParam(required = false) Category searchCategory){
        if(searchCategory==null){return service.getAllJokesContaining(contains);}
        return service.getAllJokesContaining(contains, searchCategory);
    }

}
