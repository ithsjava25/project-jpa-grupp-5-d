package org.example.pojo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actorName;

    private String country;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    // getters & setters

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setActorName(String actorName){
        this.actorName = actorName;
    }

    public String getActorName() {
        return actorName;
    }

  public void setCountry(String country){
        this.country = country;
  }

    public String getCountry() {
        return country;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
