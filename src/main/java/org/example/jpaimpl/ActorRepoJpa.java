package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.pojo.Actor;
import org.example.pojo.Movie;
import org.example.repo.ActorRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ActorRepoJpa implements ActorRepo {

    private final EntityManager em;

    public ActorRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public Actor addActor(String name, Country country) {
        // 1. Kolla om aktören redan finns
        Optional<Actor> existing = getName(name);

        if (existing.isPresent()) {
            // 2. Om den finns, uppdatera info
            Actor actor = existing.get();
            actor.setCountry(country); // uppdatera land om det ändrats
            em.merge(actor);           // merge → uppdaterar i databasen
            return actor;
        } else {
            // 3. Om den inte finns, skapa ny
            Actor actor = new Actor();
            actor.setActorName(name);
            actor.setCountry(country);

            em.persist(actor); // persist → lägger till ny
            return actor;
        }
    }



    @Override
    public boolean deleteActor(long id) {
        Actor actor = em.find(Actor.class, id);
        if (actor != null) {
            em.remove(actor);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Actor> getById(Long id) {
        return Optional.ofNullable(em.find(Actor.class, id));
    }

    @Override
    public Optional<Actor> getName(String actorName) {
        return em.createQuery("SELECT a FROM Actor a WHERE a.actorName = :name", Actor.class)
            .setParameter("name", actorName)
            .getResultStream()
            .findFirst();
    }

    @Override
    public List<Actor> getCountry(Country country) {

        if (country == null) {
            return List.of();
        }

        return em.createQuery(
                "SELECT a FROM Actor a WHERE a.country = :count",
                Actor.class)
            .setParameter("count", country)
            .getResultList();
    }

}

