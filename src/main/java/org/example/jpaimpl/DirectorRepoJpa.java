package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.repo.DirectorRepo;

import java.util.List;
import java.util.Optional;

public class DirectorRepoJpa implements DirectorRepo {

    private final EntityManager em;

    public DirectorRepoJpa(EntityManager em){
        this.em = em;
    }


    @Override
    public Director addDirector(String name, Country country) {
        Optional<Director> existing = findByName(name);

        if (existing.isPresent()) {
            Director director = existing.get();
            director.setCountry(country);
            em.merge(director);
            return director;
        } else {
            Director director = new Director();
            director.setDirectorName(name);
            director.setCountry(country);
            em.persist(director);
            return director;
        }
    }


    @Override
    public boolean deleteDirector(long id) {
        Director director = em.find(Director.class, id);
            if (director != null) {
                em.remove(director);
                return true;
        }
        return false;
    }
    public boolean deleteByName(String name) {
        Director director = em.createQuery("SELECT d FROM Director d WHERE d.directorName = :name", Director.class)
            .setParameter("name", name)
            .getResultStream()
            .findFirst()
            .orElse(null);

        if (director == null) {
            return false; // ingen actor hittades
        }

        em.remove(director);
        return true; // actor togs bort
    }

    @Override
    public Optional<Director> findById(Long id) {
        return Optional.ofNullable(em.find(Director.class, id));
    }

    @Override
    public Optional<Director> findByName(String directorName) {
        return em.createQuery("SELECT d FROM Director d WHERE d.directorName = :name", Director.class)
            .setParameter("name", directorName)
            .getResultStream()
            .findFirst();
    }

    @Override
    public List<Director> getCountry(Country country) {
        if (country == null) {
            return List.of();
        }

        return em.createQuery(
                "SELECT d FROM Director d WHERE d.country = :country",
                Director.class)
            .setParameter("country", country)
            .getResultList();

    }
}
