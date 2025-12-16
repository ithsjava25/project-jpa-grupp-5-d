package org.example.repo;

import org.example.pojo.Movie;

import java.util.List;

public interface UserRatingRepo {
    List<Movie> getMoviesByRating(float minRating, float maxRating);
    public List<Object[]> getAverageRatings();
}
