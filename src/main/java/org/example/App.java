package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import org.example.pojo.*;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import java.lang.reflect.Member;

public class App {
    public static void main(String[] args) {


        final PersistenceConfiguration cfg = new HibernatePersistenceConfiguration( "emf" )
            .jdbcUrl( "jdbc:mysql://localhost:3306/ProjectHT2025" )
            .jdbcUsername( "root" )
            .jdbcPassword( "root" )
            .property("hibernate.hbm2ddl.auto","update")
            .property("hibernate.show_sql","true")
            .property("hibernate.format_sql","true")
            .property("hibernate.highlight_sql","true")
            .managedClasses(Actor.class, Director.class, Genre.class, Movie.class, User.class, UserRating.class);




    }
}
