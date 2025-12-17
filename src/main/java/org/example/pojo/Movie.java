package org.example.pojo;

import jakarta.persistence.*;
import org.example.enums.Country;
import org.example.enums.Language;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Movie {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private int length;
    private double ranking;

    // Relational fields
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
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRating> ratings = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private Language language;

    // Getters/setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public double getRanking() { return ranking; }
    public void setRanking(double ranking) { this.ranking = ranking; }
    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    // Relational getters and setters
    public Set<Actor> getActors() { return actors; }
    public void setActors(Set<Actor> actors) {
        this.actors.clear();
        if (actors != null) this.actors.addAll(actors);
    }
    public void addActor(Actor actor) { this.actors.add(actor); }
    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) {
        this.genres.clear();
        if (genres != null) this.genres.addAll(genres);
    }
    public void addGenre(Genre genre) { this.genres.add(genre); }
    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }
    public List<UserRating> getRatings() { return ratings; }
    public void setRating(UserRating rating) {
        this.ratings.add(rating);
        rating.setMovie(this);
    }

    // Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie movie)) return false;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
