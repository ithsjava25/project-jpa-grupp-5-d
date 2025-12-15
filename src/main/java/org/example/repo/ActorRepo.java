package org.example.repo;

import org.example.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepo {

    //add actor
    boolean addActor (long id, String firstName, String lastName, String country);
    //update actor
    //delete actor
    boolean deleteActor (long id);

    Optional<Actor> getId(Long id);

    Optional<Actor> getName (String name);
        // FirstName or LastName

    List<Actor> getCountry (String country);

    List<Actor> getLanguage (String language);

}
