package org.example.repo;

import org.example.entity.Actor;
import org.example.entity.Director;
import org.example.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepo {

    //add movie
    boolean addMovie(long id, String title, String date, String length, int ranking, String country, String language);
    //update movie???
    //delete movie
    boolean deleteMovie(long id);

    List<Movie> getMovies();

    Optional<Movie> getId(Long id);
    //id (PK)
    Optional<Movie>  getTitle(String title);
    //title
    List<Movie> getMovieByReleaseDate(String from, String to);
    //release
    List<Movie> getMovieByLength(int minLen, int maxLen);
    //length
    List<Movie> getMovieByRanking(int minRank, int maxRank);
    //ranking
    List<Movie> getMovieByLanguage(String language);
    //language

    List<Director> getDirectors();
    List<Actor> getActors();
}
