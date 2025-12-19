package org.example.repo;

import org.example.pojo.Movie;
import org.example.pojo.User;
import org.example.pojo.UserRating;

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
