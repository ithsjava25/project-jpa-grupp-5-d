package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.entity.Movie;
import org.example.entity.User;
import org.example.entity.UserRating;
import org.example.repo.UserRatingRepo;

import java.util.List;
import java.util.Optional;

public class UserRatingRepoJpa implements UserRatingRepo {

    private final EntityManager em;

    public UserRatingRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Movie> getMoviesByRating(double minRating, double maxRating) {
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
    public Optional<Double> getRatingForMovieByUser(User user, Movie movie) {
        if (user == null || movie == null) return Optional.empty();

        return em.createQuery(
                "SELECT ur.rating FROM UserRating ur WHERE ur.user = :user AND ur.movie = :movie",
                Double.class)
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

    @Override
    public boolean rateMovie(User user, Movie movie, double rating) {
        if (user == null || movie == null) {
            return false;
        }

        // Check if rating already exists
        Optional<UserRating> existing = em.createQuery(
                "SELECT ur FROM UserRating ur WHERE ur.user = :user AND ur.movie = :movie",
                UserRating.class)
            .setParameter("user", user)
            .setParameter("movie", movie)
            .getResultStream()
            .findFirst();

        // If already rated, update rating
        UserRating userRating;
        if (existing.isPresent()) {
            userRating = existing.get();
            userRating.setRating(rating);
            em.merge(userRating);
        } else {
            userRating = new UserRating(user, movie, rating);
            em.persist(userRating);
        }

        // Update movie ranking
        Double avg = em.createQuery(
                "SELECT AVG(ur.rating) FROM UserRating ur WHERE ur.movie = :movie",
                Double.class)
            .setParameter("movie", movie)
            .getSingleResult();

        Double rounded = avg != null ? Math.round(avg * 10.0) / 10.0 : null;

        movie.setRanking(rounded);
        em.merge(movie);

        return true;

    }

}
