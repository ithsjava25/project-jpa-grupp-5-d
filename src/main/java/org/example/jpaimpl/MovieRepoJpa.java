package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Movie;
import org.example.repo.MovieRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MovieRepoJpa implements MovieRepo {

    private final EntityManager em;

    public MovieRepoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Movie addMovie(String title, LocalDate date, int length, Country country, Language language) {
        Optional<Movie> existing = findByTitle(title);

        if (existing.isPresent()) {
            Movie movie = existing.get();
            movie.setReleaseDate(date);
            movie.setLength(length);
            movie.setCountry(country);
            movie.setLanguage(language);
            em.merge(movie);
            return movie;
        } else {
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setReleaseDate(date);
            movie.setLength(length);
            movie.setCountry(country);
            movie.setLanguage(language);
            em.persist(movie);
            return movie;
        }
    }

    @Override
    public boolean deleteMovie(long movieId) {
        if (movieId <= 0) return false;

        Movie movie = em.find(Movie.class, movieId);

        if (movie == null) return false;

        em.remove(movie);
        return true;
    }

    @Override
    public void addActorToMovie(Long movieId, Long actorId){
        Movie movie = em.find(Movie.class, movieId);
        Actor actor = em.find(Actor.class, actorId);

        movie.getActors().add(actor);
        actor.getMovies().add(movie);

        em.persist(movie);
        em.persist(actor);
    }
    public void addActorToMovie(Movie movie, Actor actor) {
        movie.getActors().add(actor);
        actor.getMovies().add(movie);
        em.persist(movie); // optional if already managed
        em.persist(actor); // optional if already managed
    }


    public void setDirector(Long movieId, Long directorId) {
        Movie movie = em.find(Movie.class, movieId);
        Director director = em.find(Director.class, directorId);

        movie.setDirector(director);
        director.getMovies().add(movie);

        em.persist(movie);
        em.persist(director);
    }


    @Override
    public List<Movie> getAllMovies() {
        return em.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Optional<Movie> getById(Long movieId) {
        if (movieId <= 0) return Optional.empty();

        return Optional.ofNullable(em.find(Movie.class, movieId));
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        if (title == null) return Optional.empty();

        return em.createQuery(
                "SELECT m FROM Movie m WHERE m.title = :title",
                Movie.class
            )
            .setParameter("title", title)
            .getResultStream()
            .findFirst();
    }

    @Override
    public List<Movie> getMovieByReleaseDate(String from, String to) {
        if (from == null || to == null) {
            return List.of();
        }

        // FORMAT OF DATE   YYYY-MM-DD
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        return em.createQuery(
            "select m from Movie m where m.releaseDate between :from and :to", Movie.class)
            .setParameter("from", fromDate)
            .setParameter("to", toDate)
            .getResultList();
    }

    @Override
    public List<Movie> getMovieByLength(int minLen, int maxLen) {

        if (minLen <= 0 || maxLen <= 0) return List.of();

        return em.createQuery(
            "select m from Movie m where m.length between :min and :max", Movie.class)
            .setParameter("min", minLen)
            .setParameter("max", maxLen)
            .getResultList();
    }

    @Override
    public List<Movie> getMovieByRanking(int minRank, int maxRank) {
        return em.createQuery(
                "SELECT ur.movie FROM UserRating ur WHERE ur.rating BETWEEN :min AND :max",
                Movie.class)
            .setParameter("min", minRank)
            .setParameter("max", maxRank)
            .getResultList();
    }

    @Override
    public List<Movie> getMovieByLanguage(Language language) {

        if (language == null) {
            return List.of();
        }

        return em.createQuery(
                "SELECT m FROM Movie m WHERE m.language = :lang",
                Movie.class)
            .setParameter("lang", language)
            .getResultList();
    }

    @Override
    public List<Movie> getByDirector(Director director) {
        return em.createQuery(
            "select m from Movie m where m.director = :director", Movie.class)
            .setParameter("director", director)
            .getResultList();

    }

    @Override
    public List<Movie> getByActor(Actor actor) {
        return em.createQuery(
                "SELECT m FROM Movie m JOIN m.actors a WHERE a = :actor",
                Movie.class)
            .setParameter("actor", actor)
            .getResultList();

    }
}
