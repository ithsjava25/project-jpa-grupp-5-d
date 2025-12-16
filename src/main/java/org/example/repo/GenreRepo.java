package org.example.repo;

import org.example.pojo.Genre;

import java.util.Optional;

public interface GenreRepo {

    Optional<Genre> getById(long id);
    Optional<Genre> getName (String name);

}
