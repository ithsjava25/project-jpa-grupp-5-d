package org.example.repo;

import org.example.pojo.Actor;

import java.util.Optional;

public interface ActorRepo {

    //add actor
    boolean addActor (String actorName, String country);
    //delete actor
    boolean deleteActor (long id);

    Optional<Actor> getById(Long id);

    Optional<Actor> getName (String actorName);
        // FirstName or LastName

}
