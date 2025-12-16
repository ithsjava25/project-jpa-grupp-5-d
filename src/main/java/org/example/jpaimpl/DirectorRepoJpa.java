package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
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
        Optional<Director> existing = getName(name);

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

    @Override
    public Optional<Director> getById(Long id) {
        return Optional.ofNullable(em.find(Director.class, id));
    }

    @Override
    public Optional<Director> getName(String directorName) {
        return em.createQuery("SELECT d FROM Director d WHERE d.directorName = :name", Director.class)
            .setParameter("name", directorName)
            .getResultStream()
            .findFirst();
    }

    @Override
    public List<Director> getCountry(Country country) {
        return List.of();
    }
}
