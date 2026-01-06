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

    Movie addMovie(String title, LocalDate date, int length, Country country, Language language);
    boolean deleteMovie(long id);

    Optional<Movie> getById(Long id);
    Optional<Movie>  findByTitle(String title);

    List<Movie> getByDirector(Director director);
    List<Movie> getByActor(Actor actor);
    List<Movie> getMovieByReleaseDate(LocalDate from, LocalDate to);
    List<Movie> getMovieByLength(int minLen, int maxLen);
    List<Movie> getMovieByRanking(int minRank, int maxRank);
    List<Movie> getMovieByLanguage(Language language);
    List<Movie> getAllMovies();
    List<Movie> getMovieByGenre (String genre);
}
