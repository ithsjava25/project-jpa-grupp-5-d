package org.example.pojo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "user_name")
    private String userName;
    private String password;

    // Relational fields
    @OneToMany(mappedBy = "user")
    private List<UserRating> ratings;
    @ManyToMany
    @JoinTable(
        name = "User_favorites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> favoriteMovies = new HashSet<>();

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Relational getters and setters
    public List<UserRating> getRatings() {
        return ratings;
    }
    public void setRatings(List<UserRating> ratings) {
        this.ratings = ratings;
    }
    public Set<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }
    public void setFavoriteMovies(Set<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    // For user_rating table
    public void addFavoriteMovie(Movie movie) {
        favoriteMovies.add(movie);
    }
    public void removeFavoriteMovie(Movie movie) {
        favoriteMovies.remove(movie);
    }

    // Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
