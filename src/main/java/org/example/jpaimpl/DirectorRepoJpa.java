package org.example.jpaimpl;

import org.example.pojo.Director;
import org.example.repo.DirectorRepo;

import java.util.List;
import java.util.Optional;

public class DirectorRepoJpa implements DirectorRepo {
    @Override
    public boolean addDirector(String directorName, String country) {
        return false;
    }

    @Override
    public boolean deleteDirector(long id) {
        return false;
    }

    @Override
    public Optional<Director> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Director> getName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Director> getCountry(String country) {
        return List.of();
    }
}
