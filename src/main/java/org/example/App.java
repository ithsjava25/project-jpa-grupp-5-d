package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceConfiguration;
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

        // Skapa EntityManagerFactory
        try (EntityManagerFactory emf = cfg.createEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {

            EntityTransaction tx = em.getTransaction();
            try{
                tx.begin();

                SeedGenres seedGenres = new SeedGenres(em);
                Map<String, Genre> genres = seedGenres.seed();
                //seedGenres.seedGenres();

                SeedActors seedActors = new SeedActors(em);
                Map<String, Actor> actors = seedActors.seed();

                SeedDirectors seedDirectors = new SeedDirectors(em);
                Map<String, Director> directors = seedDirectors.seed();
                //seedDirectors.seedDirectors();

                SeedMovies seedMovies = new SeedMovies(em);
                seedMovies.seedMovies();

                SeedUsers seedUsers = new SeedUsers(em);
                Map<String, User> users = seedUsers.seed();
                //seedUsers.seedUsers();

                tx.commit();

            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        }
    }
}
