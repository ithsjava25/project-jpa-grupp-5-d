package org.example.jpaimpl;

import org.example.pojo.Actor;
import org.example.repo.ActorRepo;

import java.util.Optional;

public class ActorRepoJpa implements ActorRepo {


    @Override
    public boolean addActor(String actorName, String country) {
        return false;
    }

    @Override
    public boolean deleteActor(long id) {
        return false;
    }

    @Override
    public Optional<Actor> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Actor> getName(String actorName) {
        return Optional.empty();
    }
}
