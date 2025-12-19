package org.example;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.jpaimpl.UserRatingRepoJpa;
import org.example.jpaimpl.UserRepoJpa;
import org.example.pojo.*;
import org.example.seed.*;

import java.util.Optional;
import java.util.Scanner;

import static org.example.DatabaseFiller.isDatabaseEmpty;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ✅ Endast seedning körs i transaktion
        JpaRunner.runInTransaction(em -> {
            if (isDatabaseEmpty(em)) {
                DatabaseFiller filler = new DatabaseFiller(em);
                filler.seedAll();
            } else {
                System.out.println("Database already contains Movies. If other data is missing disable function and run filler.seedAll()");
            }
        });

        // ✅ Öppna en EM för CLI
        try (EntityManager em = JpaUtil.getEntityManager()) {
            UserRepoJpa userRepoJpa = new UserRepoJpa(em);
            UserRatingRepoJpa userRatingRepoJpa = new UserRatingRepoJpa(em);
            MovieRepoJpa movieRepoJpa = new MovieRepoJpa(em);

            Optional<User> optionalUser = Optional.empty();

            // 🔁 Retry loop only for login
            while (optionalUser.isEmpty()) {
                System.out.println("**** Welcome to IMDB CLI Application ****");
                System.out.print("Please enter your username: ");
                String userName = sc.nextLine();
                System.out.print("Please enter your password: ");
                String password = sc.nextLine();

                optionalUser = userRepoJpa.validateUser(userName, password);

                if (optionalUser.isEmpty()) {
                    System.out.println("Either your username or password was incorrect.");
                    System.out.println("Do you want to try again? (Y/N)");
                    String retry = sc.nextLine().toUpperCase();
                    if (!retry.equals("Y")) {
                        System.out.println("Exiting application...");
                        return;
                    }
                }
            }
            // ✅ Successful login → no more retries
            User user = optionalUser.get();
            System.out.println("**** You logged in successfully ****");
            System.out.println("Your userID is: " + user.getId());

            if (user.getUserName().equals("Admin")) {
                new CliAdminApp(em).printOptions();
            } else {
                new CliApp().runUserMenu(sc, userRepoJpa, userRatingRepoJpa, movieRepoJpa, user);
            }
        }
    }
}

