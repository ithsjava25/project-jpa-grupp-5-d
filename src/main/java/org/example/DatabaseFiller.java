package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.pojo.*;
import org.example.seed.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DatabaseFiller {

    private final EntityManager em;

    public DatabaseFiller(EntityManager em) {
        this.em = em;
    }

    public static boolean isDatabaseEmpty(EntityManager em) {
        Long movieCount = em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class)
            .getSingleResult();
        return movieCount == 0;
    }

    public void seedAll(){
        // 1. Seeding base entities
        SeedGenres seedGenres = new SeedGenres(em);
        Map<String, Genre> genres = seedGenres.seed();

        SeedActors seedActors = new SeedActors(em);
        Map<String, Actor> actors = seedActors.seed();

        SeedDirectors seedDirectors = new SeedDirectors(em);
        Map<String, Director> directors = seedDirectors.seed();

        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        SeedUsers seedUsers = new SeedUsers(em);
        Map<String, User> users = seedUsers.seed();

        // 2. Relationer (kopplar ihop filmer med actors, directors, genres)
        SeedMovieRelations seedMovieRelations = new SeedMovieRelations(em);
        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
        seedMovieRelations.attachRelations(actors, directors, genres, movieRepo);

        // 3. Relationer (Users)
        SeedUserRelations seedUserRelations = new SeedUserRelations(em);
        seedUserRelations.seedFavoritesAndRatings(users, movies);
    }
}
