package org.example.pojo;

import jakarta.persistence.*;
import org.example.enums.Country;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actorName;

    @Enumerated(EnumType.STRING)
    private Country country;

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

    public void setCountry(Country country){
        this.country = country;
  }

    public Country getCountry() {
        return country;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Actor actor)) return false;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
