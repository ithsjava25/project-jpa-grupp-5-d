package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceConfiguration;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.jpaimpl.UserRepoJpa;
import org.example.pojo.*;
import org.example.repo.UserRepo;
import org.example.seed.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import java.util.Map;
import java.util.Scanner;

public class App {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("**** Welcome to IMDB CLI Application ****");
        System.out.print("Please enter your username: ");
        String userName = sc.nextLine();
        System.out.print("Please enter your password: ");
        String password = sc.nextLine();

        try (EntityManagerFactory emf = cfg.createEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();

                UserRepoJpa userRepo = new UserRepoJpa(em);

                if(userRepo.validateUser(userName, password).isPresent()) {
                    System.out.println("**** You logged in succesfully ****");
                } else {
                    System.out.println("Either your username or password was incorrect. Please try again");
                }

            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }

        }
    }
}
