package org.example.pojo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.example.enums.Country;

@Entity
public class Director {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String directorName;
    @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
    private Set<Movie> movies = new HashSet<>();
    //En director har potentiellt flera filmer
    @Enumerated(EnumType.STRING)
    private Country country;

    // Konstruktor
    public Director(){}
    public Director(String directorName, Country country){
        this.directorName = directorName;
        this.country = country;
    }

    // Getters and setters
    // To handle many-to-many and one-to-many relations
    public Set<Movie> getMovies() { return movies; }
    public void setMovies(Set<Movie> movies) { this.movies = movies; }
    public long getDirectorID(){return id;}
    public void setDirectorID(long id){this.id = id;}
    public String getDirectorName(){return directorName;}
    public void setDirectorName(String directorName){this.directorName = directorName;}
    public Country getCountry(){return country;}
    public void setCountry(Country country){this.country = country;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Director director)) return false;
        return Objects.equals(id, director.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
