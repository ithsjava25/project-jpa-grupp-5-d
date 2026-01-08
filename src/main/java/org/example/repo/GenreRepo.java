package org.example.repo;

import org.example.entity.Genre;

import java.util.Optional;
import java.util.List;

public interface GenreRepo {

    Genre addGenre(String genreName);
    boolean deleteGenre(long genreID);
    List<Genre> getAllGenres();

    Optional<Genre> getById(long id);
    Optional<Genre> findByName (String name);
}
