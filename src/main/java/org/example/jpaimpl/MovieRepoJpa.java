package org.example.jpaimpl;

import jakarta.persistence.Entity;
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
        Optional<Movie> existing = getTitle(title);

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
    public List<Movie> getAllMovies() {
        return em.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Optional<Movie> getById(Long movieId) {
        if (movieId <= 0) return Optional.empty();

        return Optional.ofNullable(em.find(Movie.class, movieId));
    }

    @Override
    public Optional<Movie> getTitle(String title) {
        if (title == null) return Optional.empty();

        return Optional.ofNullable(em.find(Movie.class, title));
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
        if (minRank < 0 || minRank > 5 || maxRank < 0 || maxRank > 5) return List.of();

        return em.createQuery(
            "select m from Movie m where m.ranking between :min and :max", Movie.class)
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
        return em.createQuery("" +
            "select m from Movie m where m.actor = :actor", Movie.class)
            .setParameter("actor", actor)
            .getResultList();
    }
}
