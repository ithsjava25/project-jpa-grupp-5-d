package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.pojo.Genre;
import org.example.repo.GenreRepo;

import java.util.List;
import java.util.Optional;

public class GenreRepoJpa implements GenreRepo {

    private final EntityManager em;

    public GenreRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional
    public Genre addGenre(String genreName) {
        Optional<Genre> existing = getByName(genreName);

        if (existing.isPresent()) {
            return existing.get(); // inget att uppdatera för genre
        } else {
            Genre genre = new Genre();
            genre.setGenreName(genreName);
            em.persist(genre);
            return genre;
        }
    }


    @Override
    public boolean deleteGenre(long genreID){
        Genre genre = em.find(Genre.class, genreID);

        if (genre == null) return false;

        em.remove(genre);
        return true;
    }

    @Override
    public List<Genre> getAllGenres(){
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    @Override
    public Optional<Genre> getById(long genreID) {
        if (genreID <= 0) return Optional.empty();

        return Optional.ofNullable(em.find(Genre.class, genreID));
    }

    @Override
    public Optional<Genre> getByName(String genreName) {
       if (genreName == null) return Optional.empty();

       return em.createQuery("SELECT g FROM Genre g WHERE g.genreName = :name", Genre.class)
            .setParameter("name", genreName)
            .getResultStream()
            .findFirst();
    }
}
