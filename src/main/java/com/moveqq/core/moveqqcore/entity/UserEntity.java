package com.moveqq.core.moveqqcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String login;

    private String password;

    private String email;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MovieEntity> movies;

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
