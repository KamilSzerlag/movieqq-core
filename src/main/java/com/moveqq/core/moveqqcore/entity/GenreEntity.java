package com.moveqq.core.moveqqcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity{

    private String name;

    @ManyToMany(targetEntity = MovieEntity.class, fetch = FetchType.EAGER)
    private List<MovieEntity> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }
}
