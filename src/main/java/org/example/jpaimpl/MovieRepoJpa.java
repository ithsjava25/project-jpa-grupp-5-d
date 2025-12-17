package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.pojo.Movie;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MovieRepoJpa {

    private final EntityManager em;

    public MovieRepoJpa(EntityManager em) {
        this.em = em;
    }


    public Movie addMovie(String title,
                          LocalDate releaseDate,
                          int length,
                          Country country,
                          Language language) {
        Optional<Movie> existing = findByTitleOptional(title);

        if (existing.isPresent()) {
            Movie movie = existing.get();
            // uppdatera metadata om det skiljer sig
            if (!movie.getReleaseDate().equals(releaseDate)) {
                movie.setReleaseDate(releaseDate);
            }
            if (movie.getLength() != length) {
                movie.setLength(length);
            }
            if (movie.getCountry() != country) {
                movie.setCountry(country);
            }
            if (movie.getLanguage() != language) {
                movie.setLanguage(language);
            }
            return movie; // redan managed
        } else {
            Movie movie = new Movie();
            movie.setTitle(title.trim());
            movie.setReleaseDate(releaseDate);
            movie.setLength(length);
            movie.setCountry(country);
            movie.setLanguage(language);
            em.persist(movie);
            return movie;
        }
    }

    // Utökad add med relationer
    @Transactional
    public Movie addMovie(String title,
                          LocalDate releaseDate,
                          int length,
                          Country country,
                          Language language,
                          Director director,
                          List<Actor> actors,
                          List<Genre> genres) {
        Optional<Movie> existing = findByTitleOptional(title);

        if (existing.isPresent()) {
            Movie movie = existing.get();
            movie.setDirector(director);
            movie.setActors(Set.copyOf(actors));
            movie.setGenres(Set.copyOf(genres));
            return movie;
        } else {
            Movie movie = new Movie();
            movie.setTitle(title.trim());
            movie.setReleaseDate(releaseDate);
            movie.setLength(length);
            movie.setCountry(country);
            movie.setLanguage(language);
            movie.setDirector(director);
            movie.setActors(Set.copyOf(actors));
            movie.setGenres(Set.copyOf(genres));
            em.persist(movie);
            return movie;
        }
    }

    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public Optional<Movie> findByTitleOptional(String title) {
        if (title == null || title.isBlank()) {
            return Optional.empty();
        }
        TypedQuery<Movie> q = em.createQuery(
            "SELECT m FROM Movie m WHERE LOWER(TRIM(m.title)) = LOWER(TRIM(:title))",
            Movie.class);
        q.setParameter("title", title.trim());
        return q.getResultStream().findFirst();
    }

    public Movie findByTitle(String title) {
        return findByTitleOptional(title).orElseThrow();
    }

    @Transactional
    public boolean deleteMovie(long id) {
        Movie movie = em.find(Movie.class, id);
        if (movie != null) {
            em.remove(movie);
            return true;
        }
        return false;
    }

    @Transactional
    public Movie update(Movie movie) {
        return em.merge(movie);
    }

    // Hjälpmetoder för relationer
    @Transactional
    public void setDirector(String title, Director director) {
        Movie m = findByTitleOptional(title).orElse(null);
        if (m != null) {
            m.setDirector(director);
            em.merge(m);
        }
    }

    @Transactional
    public void addActors(String title, List<Actor> actors) {
        Movie m = findByTitleOptional(title).orElse(null);
        if (m != null) {
            m.getActors().addAll(actors);
            em.merge(m);
        }
    }

    @Transactional
    public void addGenres(String title, List<Genre> genres) {
        Movie m = findByTitleOptional(title).orElse(null);
        if (m != null) {
            m.getGenres().addAll(genres);
            em.merge(m);
        }
    }
}
