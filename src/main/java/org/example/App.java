package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
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
        try (EntityManagerFactory emf = cfg.createEntityManagerFactory()) {

            // Skapa EntityManager
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // Skicka in i konstruktorn

            DatabaseFiller filler = new DatabaseFiller(em);
            filler.seedActors();
            filler.seedDirectors();
            filler.seedMovies();
            filler.seedGenres();
            filler.seedUsers();

            em.getTransaction().commit();
            // Stäng resurser
            em.close();
        }

        //emf.close();


//        try (EntityManagerFactory emf = cfg.createEntityManagerFactory()) {
//            emf.runInTransaction( em -> {
//
//                Movie movie = em.find( Movie.class, 1);
//                IO.readln();




                /*

                Movie movie = new movie();
                var actor = em.getReference(Actor.class, 1);
                Movie.addActor( actor );
                em.persist( movie );

                //add a new member to an existing organization
                var  = em.find(Organization.class, organization.getId());
                org.addMember(new Member());
                org.addMember(new Member());
                org.addMember(new Member());

                 //without cascade for persist
                /*Profile profile = new Profile();
                profile.setBio("Oscar is an excellent programmer");
                em.persist(profile);
                User user = new User();
                user.setName("Najty");
                user.setProfile(profile);
                em.persist(user);

                //with cascade for persist
                Profile profile = new Profile();
                profile.setBio("Oscar is an excellent programmer");
                User user = new User();
                user.setName("Najty");
                user.setProfile(profile);
                em.persist(user);*/

                //Find product and all products from database
          /*  emf.runInTransaction( em -> {
               Product product = em.find(Product.class,1);
                System.out.println(product);
                System.out.println("=====");
                //JPQL
                //var query = em.createQuery("select p from Product p where p.name = 'Pixel 10'",Product.class);
                var query = em.createNativeQuery("select * from jpa.product where name = 'Pixel 10'", Product.class);
                query.getResultList().forEach(System.out::println);

            });

            emf.runInTransaction( em -> {
                System.out.println("====Remove Entity====");
                // We don't need all the field data to remove an object
               var product = em.getReference(Product.class,2);
               // System.out.println(product.getClass().getName());
               if (product != null)
                 em.remove(product);
            });*/
//
//            });
//        }
    }
}
