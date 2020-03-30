package com.galvanize.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Joke {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jokeId;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @Column
    private String joke;

    public Joke() {
    }

    public Joke(long jokeId, Category category, String joke){
        this.jokeId=jokeId;
        this.category=category;
        this.joke=joke;
    }

    public Joke(Category category, String joke){
        this.category=category;
        this.joke=joke;
    }

    public long getJokeId() {
        return jokeId;
    }

    public void setJokeId(long jokeId) {
        this.jokeId = jokeId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke1 = (Joke) o;
        return getJokeId() == joke1.getJokeId() &&
                getCategory() == joke1.getCategory() &&
                Objects.equals(getJoke(), joke1.getJoke());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJokeId(), getCategory(), getJoke());
    }
}
