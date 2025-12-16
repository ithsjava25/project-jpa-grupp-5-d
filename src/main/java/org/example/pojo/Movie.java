package org.example.pojo;

import jakarta.persistence.*;
import org.example.enums.Country;
import org.example.enums.Language;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    private int length;
    private float ranking;


    @ManyToMany
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
    //Movie - ägande sidan, Movies bestämmer vilka genrer som finns
    //En "sida" är ägare och andra är inverse (mottagare)
    //endast ägaren har JoinTable, mottagare har MappedBy
    //Movie bestämmer vilka genrer som finns

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;


    @OneToMany (mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRating> ratings = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Language language;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie movie)) return false;
        return id == movie.id && length == movie.length && Float.compare(ranking, movie.ranking) == 0 && Objects.equals(title, movie.title) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(actors, movie.actors) && Objects.equals(genres, movie.genres) && Objects.equals(director, movie.director) && Objects.equals(ratings, movie.ratings) && country == movie.country && language == movie.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, length, ranking, actors, genres, director, ratings, country, language);
    }
}
