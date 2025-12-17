package org.example.pojo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import org.example.enums.Country;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String directorName;

    @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
    private Set<Movie> movies = new HashSet<>();
    //En director har potentiellt flera filmer

    @Enumerated(EnumType.STRING)
    private Country country;

    public Director(){}
    public Director(long id, String directorName, Country country){
        this.id = id;
        this.directorName = directorName;
        this.country = country;
    }
    public long getDirectorID(){return id;}
    public void setDirectorID(long id){this.id = id;}

    public String getDirectorName(){return directorName;}
    public void setDirectorName(String directorName){this.directorName = directorName;}

    public Country getCountry(){return country;}
    public void setCountry(Country country){this.country = country;}



}
