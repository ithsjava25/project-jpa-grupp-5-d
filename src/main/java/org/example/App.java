package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceConfiguration;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import org.example.pojo.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

public class App {
    public static void main(String[] args) {


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

                DatabaseFiller filler = new DatabaseFiller(em);
                filler.seedActors();
                filler.seedDirectors();
                filler.seedMovies();
                filler.seedGenres();
                filler.seedUsers();

                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        }
    }
}
