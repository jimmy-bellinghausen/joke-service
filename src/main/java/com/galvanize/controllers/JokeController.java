package com.galvanize.controllers;

import com.galvanize.entities.Joke;
import com.galvanize.services.JokeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
