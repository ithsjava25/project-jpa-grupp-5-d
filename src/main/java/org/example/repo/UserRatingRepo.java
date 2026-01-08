package org.example.repo;

import org.example.entity.Movie;
import org.example.entity.User;
import org.example.entity.UserRating;

import java.util.List;
import java.util.Optional;

public interface UserRatingRepo {

    Optional<Double> getRatingForMovieByUser(User user, Movie movie);
    Optional<Double> getAverageRatingForMovie(Movie movie);

    boolean rateMovie(User user, Movie movie, double rating);

    List<Movie> getMoviesByRating(double minRating, double maxRating);
    List<UserRating> getRatingsByUser(User user);
    List<Movie> getMoviesRatedByUser(User user);
    List<Object[]> getAverageRatings();
}
