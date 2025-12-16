package org.example.jpaimpl;

import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Movie;
import org.example.repo.MovieRepo;

import java.util.List;
import java.util.Optional;

public class MovieRepoJpa implements MovieRepo {
    @Override
    public boolean addMovie(String title, String date, String length, int ranking, String country, String language) {
        return false;
    }

    @Override
    public boolean deleteMovie(long id) {
        return false;
    }

    @Override
    public List<Movie> getMovies() {
        return List.of();
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Movie> getTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Movie> getMovieByReleaseDate(String from, String to) {
        return List.of();
    }

    @Override
    public List<Movie> getMovieByLength(int minLen, int maxLen) {
        return List.of();
    }

    @Override
    public List<Movie> getMovieByRanking(int minRank, int maxRank) {
        return List.of();
    }

    @Override
    public List<Movie> getMovieByLanguage(String language) {
        return List.of();
    }

    @Override
    public List<Director> getDirectors() {
        return List.of();
    }

    @Override
    public List<Actor> getActors() {
        return List.of();
    }
}
