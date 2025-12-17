package org.example.repo;

import org.example.enums.Country;
import org.example.pojo.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepo {

    Actor addActor (String actorName, Country country);
    boolean deleteActor (long id);
    List<Actor> getCountry (Country country);

    Optional<Actor> findById(Long id);
    Optional<Actor> findByName (String actorName);
}
