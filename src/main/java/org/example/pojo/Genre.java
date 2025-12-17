package org.example.pojo;

import jakarta.persistence.*;
import org.example.repo.GenreRepo;

import java.util.*;

@Entity
public class Genre{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreID;

    @Column (name = "genre", length = 100)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();
    //Mottagare, mapped By, dessa filmer finns inom X genre
    //tillåter inte dupletter
    //ingen ordning - sorteras utefter ID eller av användaren genom select

    public Genre(){}

    public Genre(int genreID, String name){
        this.genreID = genreID;
        this.genreName = name;
    }

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

    @Override
    public String toString() {
        return "Genre{" +
            "genreID=" + genreID +
            ", name='" + genreName + '\'' +
            '}';
    }

    public String getGenreName() {return genreName;}

    public void setGenreName(String genreName) {this.genreName = genreName;}

    public Set<Movie> getMovies() {return movies;}

    public void setMovies(Set<Movie> movies) {this.movies = movies;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreID == genre.genreID && Objects.equals(genreName, genre.genreName)
            && Objects.equals(movies, genre.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreID, genreName, movies);
    }
}
