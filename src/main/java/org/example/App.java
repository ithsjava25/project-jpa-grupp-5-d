package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceConfiguration;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.pojo.*;
import org.example.seed.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import java.util.Map;

public class App {
    static void main(String[] args) {

        final PersistenceConfiguration cfg = new HibernatePersistenceConfiguration( "emf" )
            .jdbcUrl( "jdbc:mysql://localhost:3306/codeTraumaTeam" )
            .jdbcUsername( "user" )
            .jdbcPassword( "password" )
            .property("hibernate.hbm2ddl.auto","update")
            .property("hibernate.show_sql","true")
            .property("hibernate.format_sql","true")
            .property("hibernate.highlight_sql","true")
            .managedClasses(Actor.class, Director.class, Genre.class, Movie.class, User.class, UserRating.class);

        // Seeding database
        try (EntityManagerFactory emf = cfg.createEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();

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

                tx.commit();

            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }

        }

        /*
        validateUser(String userName, String password);
         */
    }

    public void printOptions(){
        /*
        User:
        1. Add new user (userName, password)
        2. Update password (userId, password)
        3. Delete user (userId)
        4. Get favorite movies (UserId)
        5. Add favorite movie (userId, movie)
        6. Remove favorite movie (userId, movie)
        7. Find users by username (userName)

        UserRating:
        1. Rate a movie (user, movie, rating)
        2. Get movies that you rated (user)
        3. Get movies by rating (min, max)
        4. Get your rating for a movie (user, movie)
        5. Get average rating for a movie
            **** CAN BE DELETED? DISPLAYED IN MOVIE ENTITY ****

        Movie:

        Genre:

        Director:

        Actor:



         */
    }
}
