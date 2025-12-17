package org.example.repo;

import org.example.pojo.Genre;

import java.util.Optional;
import java.util.List;

public interface GenreRepo {

    Genre addGenre(String genreName);
    boolean deleteGenre(long genreID);

    Optional<Genre> getById(long id);
    Optional<Genre> findByName (String name);

    List<Genre> getAllGenres();
}
