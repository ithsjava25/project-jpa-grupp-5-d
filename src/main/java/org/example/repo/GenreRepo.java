package org.example.repo;

import org.example.entity.Genre;

import java.util.Optional;

public interface GenreRepo {

    Optional<Genre> getId(long id);
    Optional<Genre> getName (String name);

}
