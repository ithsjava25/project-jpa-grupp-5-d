package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.example.pojo.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

public class JpaUtil {

    private static final EntityManagerFactory emf;

    static {
        final PersistenceConfiguration cfg = new HibernatePersistenceConfiguration("emf")
            .jdbcUrl("jdbc:mysql://localhost:3306/codeTraumaTeam")
            .jdbcUsername("user")
            .jdbcPassword("password")
            .property("hibernate.hbm2ddl.auto", "update")
            .property("hibernate.show_sql", "true")
            .property("hibernate.format_sql", "true")
            .property("hibernate.highlight_sql", "true")
            .managedClasses(Actor.class, Director.class, Genre.class, Movie.class, User.class, UserRating.class);

        emf = cfg.createEntityManagerFactory();
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
