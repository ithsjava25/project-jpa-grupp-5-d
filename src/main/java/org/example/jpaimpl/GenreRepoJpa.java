package org.example.jpaimpl;

import org.example.pojo.Genre;
import org.example.repo.GenreRepo;

import java.util.Optional;

public class GenreRepoJpa implements GenreRepo {
    @Override
    public Optional<Genre> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Genre> getName(String name) {
        return Optional.empty();
    }
}
