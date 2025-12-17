package org.example.repo;

import org.example.pojo.Movie;
import org.example.pojo.User;
import org.example.pojo.UserRating;

import java.util.List;
import java.util.Optional;

public interface UserRatingRepo {

    Optional<Float> getRatingForMovieByUser(User user, Movie movie);
    Optional<Double> getAverageRatingForMovie(Movie movie);

    void rateMovie(User user, Movie movie, float rating);

    List<Movie> getMoviesByRating(float minRating, float maxRating);
    List<UserRating> getRatingsByUser(User user);
    List<Movie> getMoviesRatedByUser(User user);
    List<Object[]> getAverageRatings();
}
