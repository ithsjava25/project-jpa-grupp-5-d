package org.example.repo;

import org.example.enums.Country;
import org.example.pojo.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepo {

    //add actor
    Actor addActor (String actorName, Country country);
    //delete actor
    boolean deleteActor (long id);

    Optional<Actor> getById(Long id);

    Optional<Actor> getName (String actorName);
    List<Actor> getCountry (Country country);
        // FirstName or LastName

}
