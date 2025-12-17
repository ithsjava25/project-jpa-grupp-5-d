package org.example.pojo;

import jakarta.persistence.*;
import org.example.repo.GenreRepo;

import java.util.*;

@Entity
public class Genre{

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreID;
    @Column (name = "genre", length = 100)
    private String genreName;
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    // Konstruktors
    public Genre(){}
    public Genre(int genreID, String name){
        this.genreID = genreID;
        this.genreName = name;
    }

    // Getters and setters
    public int getGenreID(){
        return genreID;
    }
    public void setGenreID(int genreID){
        this.genreID = genreID;
    }
    public String getName(){
        return genreName;
    }
    public void setName(String name){
        this.genreName = name;
    }
    public String getGenreName() {return genreName;}
    public void setGenreName(String genreName) {this.genreName = genreName;}
    public Set<Movie> getMovies() {return movies;}
    public void setMovies(Set<Movie> movies) {this.movies = movies;}

    @Override
    public String toString() {
        return "Genre{" +
            "genreID=" + genreID +
            ", name='" + genreName + '\'' +
            '}';
    }

    // Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Genre genre)) return false;
        return genreID == genre.genreID;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(genreID);
    }
}
