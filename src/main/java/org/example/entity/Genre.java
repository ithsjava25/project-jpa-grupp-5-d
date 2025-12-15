package org.example.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreID;

    //kommer inte ihåg hur man gjorde detta
    //alt. namnet blir samma som fältnamnet i entity;n
    @Column (name = "Genre", length = 100)
    private String genreName;

    //@ManyToMany(mappedBy = "genres")
    //private Set<Movie> movies = new HashSet<>();

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

}
