package org.example.repo;

import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepo {

    //add movie
    boolean addMovie(String title, String date, String length, int ranking, String country, String language);
    //update movie???
    //delete movie
    boolean deleteMovie(long id);

    List<Movie> getMovies();

    Optional<Movie> getById(Long id);
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
