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
import org.example.repo.MovieRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MovieRepoJpa implements MovieRepo {

    private final EntityManager em;

    public MovieRepoJpa(EntityManager em) {
        this.em = em;
    }


    public Movie addMovie(String title,
                          LocalDate releaseDate,
                          int length,
                          Country country,
                          Language language) {
        Optional<Movie> existing = findByTitle(title);

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
    public Movie addMovie(String title,
                          LocalDate releaseDate,
                          int length,
                          Country country,
                          Language language,
                          Director director,
                          List<Actor> actors,
                          List<Genre> genres) {
        Optional<Movie> existing = findByTitle(title);

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


    public Optional<Movie> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            return Optional.empty();
        }

        return Optional.ofNullable(em.find(Movie.class, title));
    }

    @Override
    public List<Movie> getByDirector(Director director) {
        return List.of();
    }

    @Override
    public List<Movie> getByActor(Actor actor) {
        return List.of();
    }

    @Override
    public List<Movie> getMovieByReleaseDate(String from, String to) {
        if (from == null || to == null || from.isEmpty() || to.isEmpty()) return List.of();

        return em.createQuery(
            "select m from Movie m where m.date between :from and :to order by m.date asc", Movie.class)
            .setParameter("from", from)
            .setParameter("to", to)
            .getResultList();
    }

    @Override
    public List<Movie> getMovieByLength(int minLen, int maxLen) {
        if (minLen < 0 || maxLen < 0) return List.of();

        return em.createQuery(
            "select m from Movie m where m.length between :minLen and :maxLen order by m.length asc", Movie.class)
            .setParameter("minLen", minLen)
            .setParameter("maxLen", maxLen)
            .getResultList();
    }

    @Override
    public List<Movie> getMovieByRanking(int minRank, int maxRank) {
        if (minRank <= 0 || minRank > 5 || maxRank <= 0 || maxRank > 5) return List.of();

        return em.createQuery(
                "select m from Movie m where m.ranking between :minRank and :maxRank order by m.ranking asc",
                Movie.class)
            .setParameter("minRank", minRank)
            .setParameter("maxRank", maxRank)
            .getResultList();

    }

    @Override
    public List<Movie> getMovieByLanguage(Language language) {
        if (language == null){
            return List.of();
        }

        return em.createQuery(
            "select m from Movie m where language = :language", Movie.class)
            .setParameter("language", language)
            .getResultList();

    }

    @Override
    public List<Movie> getAllMovies() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public boolean deleteMovie(long id) {
        Movie movie = em.find(Movie.class, id);
        if (movie != null) {
            em.remove(movie);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public List<Movie> getMovieByGenre (String genre) {
        if (genre == null){
            return List.of();
        }

        return em.createQuery(
            "select m from Movie m join m.genres g where g.genreName = :genreName",
            Movie.class)
            .setParameter("genreName", genre)
            .getResultList();

    }

    // Hjälpmetoder för relationer
    public void setDirector(String title, Director director) {
        Movie m = findByTitle(title).orElse(null);
        if (m != null) {
            m.setDirector(director);
            em.merge(m);
        }
    }
    public void addActors(String title, List<Actor> actors) {
        Movie m = findByTitle(title).orElse(null);
        if (m != null) {
            m.getActors().addAll(actors);
            em.merge(m);
        }
    }
    public void addGenres(String title, List<Genre> genres) {
        Movie m = findByTitle(title).orElse(null);
        if (m != null) {
            m.getGenres().addAll(genres);
            em.merge(m);
        }
    }



}
