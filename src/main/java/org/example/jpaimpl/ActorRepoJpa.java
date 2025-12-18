package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.pojo.Actor;
import org.example.repo.ActorRepo;

import java.util.List;
import java.util.Optional;

public class ActorRepoJpa implements ActorRepo {

    private final EntityManager em;

    public ActorRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public Actor addActor(String name, Country country) {
        // 1. Kolla om aktören redan finns (case-insensitive + trim)
        Optional<Actor> existing = findByName(name);

        if (existing.isPresent()) {
            // 2. Om den finns, uppdatera info om det behövs
            Actor actor = existing.get();
            if (actor.getCountry() != country) {
                actor.setCountry(country); // ändra land om det skiljer sig
            }
            // Ingen merge behövs eftersom actor redan är managed
            return actor;
        } else {
            // 3. Om den inte finns, skapa ny
            Actor actor = new Actor();
            actor.setActorName(name.trim());
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
    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(em.find(Actor.class, id));
    }

    @Override
    public Optional<Actor> findByName(String actorName) {
        if (actorName == null || actorName.isBlank()) {
            return Optional.empty();
        }

        return em.createQuery(
                "SELECT a FROM Actor a WHERE LOWER(TRIM(a.actorName)) = LOWER(TRIM(:name))",
                Actor.class)
            .setParameter("name", actorName.trim())
            .getResultStream()
            .findFirst();
    }
    @Override
    public List<Actor> getCountry(Country country) {

        if (country == null) {
            return List.of();
        }

        return em.createQuery(
                "SELECT a FROM Actor a WHERE a.country = :country",
                Actor.class)
            .setParameter("country", country)
            .getResultList();
    }

}

