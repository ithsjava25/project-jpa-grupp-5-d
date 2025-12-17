package org.example.pojo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_rating")
public class UserRating {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;

    // Relational fields
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Konstruktors
    public UserRating() {}
    public UserRating(User user, Movie movie, float rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    // Relational getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    // Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserRating that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
