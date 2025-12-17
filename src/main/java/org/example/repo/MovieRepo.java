package org.example.repo;

import org.example.enums.Country;
import org.example.enums.Language;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepo {

    //add movie
    Movie addMovie(String title, LocalDate date, int length, Country country, Language language);
    //update movie???
    //delete movie
    boolean deleteMovie(long id);

    List<Movie> getAllMovies();

    Optional<Movie> getById(Long id);
    //id (PK)
    Optional<Movie>  findByTitle(String title);
    //title
    List<Movie> getMovieByReleaseDate(String from, String to);
    //release
    List<Movie> getMovieByLength(int minLen, int maxLen);
    //length
    List<Movie> getMovieByRanking(int minRank, int maxRank);
    //ranking
    List<Movie> getMovieByLanguage(Language language);
    //language

    void addActorToMovie(Long movieId, Long actorId);
    void addActorToMovie(Movie movie, Actor actor);
    void setDirector(Long movieId, Long directorId);


    List<Movie> getByDirector(Director director);
    List<Movie> getByActor(Actor actor);
}
