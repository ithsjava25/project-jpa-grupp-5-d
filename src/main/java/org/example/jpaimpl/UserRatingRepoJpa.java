package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.pojo.Movie;
import org.example.pojo.User;
import org.example.pojo.UserRating;
import org.example.repo.UserRatingRepo;

import java.util.List;
import java.util.Optional;

public class UserRatingRepoJpa implements UserRatingRepo {

    private final EntityManager em;

    public UserRatingRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Movie> getMoviesByRating(float minRating, float maxRating) {
        return em.createQuery(
            "select ur.movie from UserRating ur where ur.rating between :min and :max", Movie.class)
            .setParameter("min", minRating)
            .setParameter("max", maxRating)
            .getResultList();

    }

    public List<Object[]> getAverageRatings() {
        return em.createQuery(
                "SELECT ur.movie, AVG(ur.rating) FROM UserRating ur GROUP BY ur.movie",
                Object[].class)
            .getResultList();
    }

    @Override
    public List<UserRating> getRatingsByUser(User user) {
        if (user == null) return List.of();

        return em.createQuery(
                "SELECT ur FROM UserRating ur WHERE ur.user = :user",
                UserRating.class)
            .setParameter("user", user)
            .getResultList();
    }

    @Override
    public List<Movie> getMoviesRatedByUser(User user) {
        if (user == null) return List.of();

        return em.createQuery(
                "SELECT ur.movie FROM UserRating ur WHERE ur.user = :user",
                Movie.class)
            .setParameter("user", user)
            .getResultList();
    }

    @Override
    public Optional<Float> getRatingForMovieByUser(User user, Movie movie) {
        if (user == null || movie == null) return Optional.empty();

        return em.createQuery(
                "SELECT ur.rating FROM UserRating ur WHERE ur.user = :user AND ur.movie = :movie",
                Float.class)
            .setParameter("user", user)
            .setParameter("movie", movie)
            .getResultStream()
            .findFirst();
    }

    @Override
    public Optional<Double> getAverageRatingForMovie(Movie movie) {
        if (movie == null) return Optional.empty();

        return em.createQuery(
                "SELECT AVG(ur.rating) FROM UserRating ur WHERE ur.movie = :movie",
                Double.class)
            .setParameter("movie", movie)
            .getResultStream()
            .findFirst();
    }


}
